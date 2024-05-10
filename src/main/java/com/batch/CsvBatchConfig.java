package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

    @Bean
    public ItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data.csv"));
        
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"firstName", "lastName"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemWriter<Person> writer() {
        return items -> {
            for (Person person : items) {
                // 在这里可以将解析的数据进行处理，例如保存到数据库
                System.out.println("Processed: " + person);
            }
        };
    }

    @Bean
    public Job csvParsingJob(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("csvParsingJob")
            .start(step)
            .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer) {
        return stepBuilderFactory.get("step")
            .<Person, Person>chunk(10)
            .reader(reader)
            .writer(writer)
            .build();
    }
}
