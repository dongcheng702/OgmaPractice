package com.cms.service.benefitcalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.benefitcalculation.BenefitcalculationBean;
import com.cms.mapper.benefitcalculation.BenefitcalculationMapper;

//表明这是一个Spring的服务类
@Service
public class BenefitcalculationServiceImpl implements BenefitcalculationService {

	@Autowired
	BenefitcalculationMapper mapper;

	private static List<BenefitcalculationBean> ret;
	Map<String, List<BenefitcalculationBean>> storeGroup;

	@Override
	public List<BenefitcalculationBean> getBenefitData(String id, String month) throws Exception {
		BenefitcalculationBean bean = new BenefitcalculationBean();
		ret = new ArrayList<>();

		bean.setStoreId(id);
		bean.setSalesDay(month);

		int storeCount = mapper.selectID(bean);
		if (storeCount == 0) {
			throw new IllegalStateException("店舗は存在しません。");
		}

		List<BenefitcalculationBean> results = mapper.select(bean);
		if (results.size() == 0) {
			throw new IllegalStateException("売上情報は存在しません。");
		}

		storeGroup = groupBy(results);

		calculateStoreData(storeGroup);

		insertOrUpData();

		return mapper.selectBenefit(bean);
	}

	// 分组
	public static Map<String, List<BenefitcalculationBean>> groupBy(List<BenefitcalculationBean> results) {
		Map<String, List<BenefitcalculationBean>> groupedMap = new HashMap<>();
		// 遍历结果列表
		for (BenefitcalculationBean bean : results) {
			String storeId_month = bean.getStoreId() + "=" + bean.getSalesDay().substring(0, 7); // 检查是否已存在该 storeId
																									// 对应的列表，若不存在则创建新列表
			if (!groupedMap.containsKey(storeId_month)) {
				groupedMap.put(storeId_month, new ArrayList<>());
			}
			// 将对象存储到对应的列表中
			groupedMap.get(storeId_month).add(bean);
		}
		return groupedMap;
	}

	// 计算数据
	private static void calculateStoreData(Map<String, List<BenefitcalculationBean>> idGroup) {
		// 合計売買件数
		int totalSalesCount;
		// 合計売買金額
		int totalSalesAmount;
		// 合計仕入金額
		int totalPurchaseAmount;
		// 合計利益
		int totalBenefit;

		int sizeCount;

		for (Entry<String, List<BenefitcalculationBean>> entry : idGroup.entrySet()) {
			totalSalesCount = 0;
			totalSalesAmount = 0;
			totalPurchaseAmount = 0;
			totalBenefit = 0;
			sizeCount = 0;

			for (BenefitcalculationBean result : entry.getValue()) {
				System.out.println(result);
				// 单个商品的销售数量、销售金额和采购金额
				int salesCount = result.getSalesCount();
				int salesAmount = salesCount * result.getSalesPrice();
				int purchaseAmount = salesCount * result.getPurchasePrice();
				// 利润
				int benefit = salesAmount - purchaseAmount;
				// 将单个商品的销售数量、销售金额和采购金额累加到总值中
				totalSalesCount += salesCount;
				totalSalesAmount += salesAmount;
				totalPurchaseAmount += purchaseAmount;
				totalBenefit += benefit;
				if (++sizeCount == entry.getValue().size()) {
					result.setSalesCount(totalSalesCount);
					result.setSalesAmount(totalSalesAmount);
					result.setPurchaseAmount(totalPurchaseAmount);
					result.setBenefit(totalBenefit);
					ret.add(result);
				}
			}
		}
	}

	// 插入和更新数据
	private void insertOrUpData() {
		List<BenefitcalculationBean> retdata;
		for (BenefitcalculationBean data : ret) {
			BenefitcalculationBean bean = new BenefitcalculationBean();
			bean.setStoreId(data.getStoreId());
			bean.setSalesDay(data.getSalesDay().substring(0, 7));
			retdata = mapper.selectBenefit(bean);
			if (retdata.size() == 0) {
				data.setBenefitId(mapper.countBenefit() + 1);
				mapper.insertBenfit(data);
			} else {
				BenefitcalculationBean benefitData = retdata.get(0);
				if (benefitData.getStoreId().equals(data.getStoreId())
						&& !benefitData.getSalesMonth().equals(data.getSalesDay().substring(0, 7))) {
					data.setBenefitId(mapper.countBenefit() + 1);
					mapper.insertBenfit(data);
				} else if (benefitData.getStoreId().equals(data.getStoreId())
						&& benefitData.getSalesMonth().equals(data.getSalesDay().substring(0, 7))
						&& benefitData.getSalesCount() != data.getSalesCount()) {
					mapper.updateBenefit(data);
				}
			}
		}
	}
}