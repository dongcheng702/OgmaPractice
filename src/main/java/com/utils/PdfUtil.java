package com.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;


public class PdfUtil {

	/**
     *PDFファイル作成
     */
	public final static void printPdf(String tmpFilePath) {

//		printPdf("C:/Users/81804/Desktop/output/PdfTemple.pdf");
		try {
			//PDFフォーム取得
			PDDocument pdfDocument = Loader.loadPDF(new File(tmpFilePath));

			// 帳票フォーム内容を取得する
			PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

			if (acroForm != null) {
                //電話
				PDTextField phone = (PDTextField) acroForm.getField("phone");
				phone.setValue("0101234567");
                //Fax
				PDTextField fax = (PDTextField) acroForm.getField("fax");
				fax.setValue("0101234568");
                //Fax
				PDTextField amount = (PDTextField) acroForm.getField("amount");
				amount.setValue("9999999");
			}
			
			// システム日時を取得する
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String sysDate = now.format(formatter);
			
			//PDF出力
			pdfDocument.save("C:/work/pdf/output/請求書_" + sysDate + ".pdf");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}