package org.calminfotech.inventory.utils;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/*import org.calminfotech.consultation.models.VisitConsultationPrescribedDrug;
import org.calminfotech.consultation.models.VisitConsultationPrescriptionMeasurement;*/
import org.calminfotech.inventory.daoInterface.InventoryDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.models.StockMeasurement;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.ClockedUsersList;
import org.calminfotech.utils.PrescribedstatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;
//import org.calminfotech.system.models.UnitItem;

@Service
public class UnitOfMeasureConverter {

	@Autowired
	private InventoryDaoInterface inventoryDaoImpl;

	@Autowired
	private ClockedUsersList clockuserBo;

	@Autowired
	private GlobalItemUnitofMeasureBo globalItemUnitbo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private PrescribedstatusList prescribedstatusBo;

	@Autowired
	private InventoryManagerInterface inventoryManagerImpl;

	public int convertmeasuretounits(int totalContainedUnit,
			Integer unitOfMeasureToConvert)
			throws InvalidUnitOfMeasureConfiguration {

		// if unit of measure to convert to/selected by user is unit, then we
		// simply return the qty
		// int unit = AppConfig.UNIT_OF_MEASURE_UNIT;

		// System.out.println(globalItemId + "/itmid/" +
		// unitOfMeasureToConvert);

		// UnitItem globalItemUnitofMeasure = null;
		GlobalItemUnitofMeasureVw globalItemUnitofMeasure = null;

		if (unitOfMeasureToConvert != null) {
			globalItemUnitofMeasure = this.inventoryDaoImpl
					.getUnitOfMeasureFromDetailsRwId(unitOfMeasureToConvert);

			if (globalItemUnitofMeasure == null) {
				throw new InvalidUnitOfMeasureConfiguration(
						"Contained Measure Id  measure does not exist: "
								+ unitOfMeasureToConvert);
			}

			/*if (this.globalItemUnitbo.getGlobalItemUnitOfMeasurementsIsnull(
					globalItemUnitofMeasure.getGlobalitem_id()).size() != 1) {
				throw new InvalidUnitOfMeasureConfiguration(
						"Measure with Contained  Nothing does not exist or exist more than once  in the measure configuration:");

			}*/

		}

		/*
		 * if no record found and current unit of measure is unit, then unit has
		 * not been configured for current item
		 */
		if (globalItemUnitofMeasure == null) {
			if (unitOfMeasureToConvert == null) {
				// return
				// totalContainedUnit*=globalItemUnitofMeasure.getContainedValue();
				System.out.print("end/" + totalContainedUnit + "/"
						+ unitOfMeasureToConvert);
				// CHECK THE UNIT OF MEASURE TABLE
				return totalContainedUnit;
			} else {
				throw new InvalidUnitOfMeasureConfiguration(
						"invalid Contained unit of measure configuration or contained does not exist:"
								+ unitOfMeasureToConvert);
			}

		} else {

			// totalContainedUnit *= globalItemUnitofMeasure.getContdValue();
			totalContainedUnit *= globalItemUnitofMeasure.getContainedvalue();

			System.out.print("totalcontain/" + totalContainedUnit + "/"
					+ unitOfMeasureToConvert);
			if (unitOfMeasureToConvert != globalItemUnitofMeasure
					.getContainedmeasure_id()) {
				return this.convertmeasuretounits(totalContainedUnit,
						globalItemUnitofMeasure.getContainedmeasure_id());
			} else {
				throw new InvalidUnitOfMeasureConfiguration(
						"unit of measure cannot be converted to itself:"
								+ unitOfMeasureToConvert);
			}

		}

	}

	public int convertmeasuretounits_keepforunitidea(int totalContainedUnit,
			Integer unitOfMeasureToConvert)
			throws InvalidUnitOfMeasureConfiguration {

		// if unit of measure to convert to/selected by user is unit, then we
		// simply return the qty
		// int unit = AppConfig.UNIT_OF_MEASURE_UNIT;

		// System.out.println(globalItemId + "/itmid/" +
		// unitOfMeasureToConvert);

		// UnitItem globalItemUnitofMeasure = null;

		GlobalItemUnitofMeasureVw globalItemUnitofMeasure = null;

		// if (unitOfMeasureToConvert != null) {
		globalItemUnitofMeasure = this.inventoryDaoImpl
				.getUnitOfMeasureFromDetailsRwId(unitOfMeasureToConvert);

		if (globalItemUnitofMeasure == null) {

			throw new InvalidUnitOfMeasureConfiguration(
					"Contained Measure Id  measure does not exist: "
							+ unitOfMeasureToConvert);

		}

		/*
		 * if (this.inventoryDaoImpl.getGlobalItemUnitOfMeasurementsIsnull(
		 * globalItemUnitofMeasure.getGlobalitem_id()).size() != 1) { throw new
		 * InvalidUnitOfMeasureConfiguration(
		 * "Contained null does not exist or more than one in measure configuration:"
		 * );
		 * 
		 * }
		 */
		else {
			return globalItemUnitofMeasure.getContainedvalue()
					* totalContainedUnit;

		}

	}

	/*
	 * if no record found and current unit of measure is unit, then unit has not
	 * been configured for current item
	 */
	/*
	public Set<VisitConsultationPrescriptionMeasurement> convertunitstomeasure(
			int prescriptionQtyp,
			List<GlobalItemUnitofMeasureVw> unitOfMeasurements,
			VisitConsultationPrescribedDrug visitPrescribedDrug)
			throws InvalidUnitOfMeasureConfiguration {

	Set<VisitConsultationPrescriptionMeasurement> prescriptionMeasurements = null;

		if (unitOfMeasurements != null) {

			if (visitPrescribedDrug != null) {
				GlobalItem globalItem = visitPrescribedDrug.getGlobalitem();
				prescriptionMeasurements = new HashSet();
				List<Map<String, Object>> wrapperList = new ArrayList();
				Map<String, Object> unitOfMeasureInfoMap = null;
				int unitOfMeasureQty = 0;
				int remainderQty = 0;

				// get contained quantities/units
				// for (UnitItem unitOfMeasurement : unitOfMeasurements) {
				for (GlobalItemUnitofMeasureVw unitOfMeasurement : unitOfMeasurements) {

					unitOfMeasureInfoMap = new HashMap();
					unitOfMeasureInfoMap
							.put("unitofmeasure", unitOfMeasurement);
					
					 * unitOfMeasureInfoMap.put("contained_unit", this
					 * .convertUnitOfMeasureToUnitOptionB( globalItem.getId(),
					 * AppConfig.UNIT_OF_MEASURE_UNIT, 0,
					 * unitOfMeasurement.getUnitId()));
					 
					unitOfMeasureInfoMap.put(
							"qty",
							this.convertmeasuretounits(1,
									unitOfMeasurement.getId()

							));
					wrapperList.add(unitOfMeasureInfoMap);
				}

				// sort list
				Collections.sort(wrapperList, new SortUnitOfMeasureByQty());
				int unitOfMeasureId = 0;
				int prescriptionQty = 0;
				int ct = 0;
				// process
				// Math.abs is an alternative to this style
				if (prescriptionQtyp < 0) {
					System.out.print("Less ooo 123");
					prescriptionQty = prescriptionQtyp * -1;
					System.out.print("pp" + prescriptionQty);
				} else {
					prescriptionQty = prescriptionQtyp;
				}

				// System.out.println("wraplst/" + wrapperList);

				// process
				for (Map<String, Object> unitOfMeasureInfo : wrapperList) {

					unitOfMeasureQty = (Integer) unitOfMeasureInfo.get("qty");
					unitOfMeasureId = ((GlobalItemUnitofMeasureVw) unitOfMeasureInfo
							.get("unitofmeasure")).getId();
					if (unitOfMeasureQty > prescriptionQty) {
						continue;
					}

					ct = ct + 1;
					remainderQty = prescriptionQty / unitOfMeasureQty;

					int rQty = 0;

					// Math.abs is an alternative to this style

					if (prescriptionQtyp < 0) {
						System.out.print("less ooo");
						rQty = remainderQty * -1;

					}

					else {
						System.out.print("Not less ooo");
						rQty = remainderQty;

					}

					prescriptionMeasurements.add(this
							.getNewPresrciptionMeasurement(unitOfMeasureId,
									rQty, visitPrescribedDrug));

					prescriptionQty = prescriptionQty
							- (remainderQty * unitOfMeasureQty);
				}

				if (prescriptionQty >= 0) {

					int rQty = 0;
					if (prescriptionQtyp < 0) {
						rQty = prescriptionQty * -1;

					}

					else {
						rQty = prescriptionQty;
					}

					if (rQty == 0 && ct < 1) {
						prescriptionMeasurements.add(this
								.getNewPresrciptionMeasurement(
										AppConfig.UNIT_OF_MEASURE_UNIT, rQty,
										visitPrescribedDrug));
					}

					if (rQty > 0)

					{
						prescriptionMeasurements.add(this
								.getNewPresrciptionMeasurement(
										AppConfig.UNIT_OF_MEASURE_UNIT, rQty,
										visitPrescribedDrug));
					}

				}

			}
		}
		return prescriptionMeasurements;

	}*/

	private class SortUnitOfMeasureByQty implements
			Comparator<Map<String, Object>> {

		@Override
		public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			int retVal = 0;
			int qty1 = (Integer) o1.get("qty");
			int qty2 = (Integer) o2.get("qty");
			if (qty1 < qty2) {
				retVal = 1;
			} else if (qty1 == qty2) {
				retVal = 0;
			} else if (qty1 > qty2) {
				retVal = -1;
			}
			return retVal;
		}

	}

	/*
	 * recursive method to convert prescribed drug dosage qty to unit of measure
	 * quantity
	 */

	/*
	 * create unit of measurement object for this prescribed drug and return
	 */
/*	private VisitConsultationPrescriptionMeasurement getNewPresrciptionMeasurement(
			int unitOfMeasureId, int unitOfMeasureQty,
			VisitConsultationPrescribedDrug visitPrescribedDrug) {
		VisitConsultationPrescriptionMeasurement prescriptionMeasurement = new VisitConsultationPrescriptionMeasurement();
		// GlobalUnitofMeasure g = new GlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw g = new GlobalItemUnitofMeasureVw();

		g.setId(unitOfMeasureId);
		prescriptionMeasurement.setGlobalitemUnitofMeasurevw(g);
		prescriptionMeasurement
				.setConsultationprescribedDrug(visitPrescribedDrug);
		prescriptionMeasurement.setQuantity(unitOfMeasureQty);
		prescriptionMeasurement.setQtydisp(unitOfMeasureQty);

		prescriptionMeasurement.setCreatedBy(this.userIdentity.getUsername());
		prescriptionMeasurement.setCreatedDate(new Date());
		prescriptionMeasurement.setPrescribedststatus(this.prescribedstatusBo
				.getPrescribedstatusById(1));

		// prescriptionMeasurements.add(prescriptionMeasurement);
		return prescriptionMeasurement;
	}*/

	/*
	 * recursive method to convert prescribed drug dosage qty to unit of measure
	 * quantity
	 */
/*
	public Set<StockMeasurement> convertunitstomeasure(int prescriptionQtyp,
			GlobalItem globalItem) throws InvalidUnitOfMeasureConfiguration {

		Set<StockMeasurement> prescriptionMeasurements = null;
		System.out
				.print("Am passing in the converter" + globalItem.getItemId());
		List<GlobalItemUnitofMeasureVw> unitOfMeasurements = this.globalItemUnitbo
				.getGlobalItemUnitOfMeasurements(globalItem.getItemId());

		System.out.print("Measure list size" + unitOfMeasurements.size());

		if (unitOfMeasurements != null) {

			// GlobalItem globalItem = visitPrescribedDrug.getGlobalitem();
			prescriptionMeasurements = new HashSet();
			List<Map<String, Object>> wrapperList = new ArrayList();
			Map<String, Object> unitOfMeasureInfoMap = null;
			int unitOfMeasureQty = 0;
			int remainderQty = 0;

			// get contained quantities/units
			// for (UnitItem unitOfMeasurement : unitOfMeasurements) {
			for (GlobalItemUnitofMeasureVw unitOfMeasurement : unitOfMeasurements) {

				unitOfMeasureInfoMap = new HashMap();
				unitOfMeasureInfoMap.put("unitofmeasure", unitOfMeasurement);
				/*
				 * unitOfMeasureInfoMap.put("contained_unit", this
				 * .convertUnitOfMeasureToUnitOptionB( globalItem.getId(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT, 0,
				 * unitOfMeasurement.getUnitId()));
				 
				unitOfMeasureInfoMap.put("qty",
						this.convertmeasuretounits(1, unitOfMeasurement.getId()

						));
				wrapperList.add(unitOfMeasureInfoMap);
			}

			// sort list
			Collections.sort(wrapperList, new SortUnitOfMeasureByQty());

			// System.out.println("wraplst/" + wrapperList);
			int unitOfMeasureId = 0;
			int prescriptionQty = 0;
			int ct = 0;
			// process
			if (prescriptionQtyp < 0) {
				System.out.print("Less ooo 123");
				prescriptionQty = prescriptionQtyp * -1;
				System.out.print("pp" + prescriptionQty);
			} else {
				prescriptionQty = prescriptionQtyp;
			}

			for (Map<String, Object> unitOfMeasureInfo : wrapperList) {

				unitOfMeasureQty = (Integer) unitOfMeasureInfo.get("qty");
				unitOfMeasureId = ((GlobalItemUnitofMeasureVw) unitOfMeasureInfo
						.get("unitofmeasure")).getId();
				if (unitOfMeasureQty > prescriptionQty) {
					continue;
				}
				ct = ct + 1;
				remainderQty = prescriptionQty / unitOfMeasureQty;

				int rQty = 0;

				if (prescriptionQtyp < 0) {
					System.out.print("less ooo");
					rQty = remainderQty * -1;

				}

				else {
					System.out.print("Not less ooo");
					rQty = remainderQty;

				}

				prescriptionMeasurements.add(this
						.getNewPresrciptionMeasurement2(unitOfMeasureId, rQty));

				prescriptionQty = prescriptionQty
						- (remainderQty * unitOfMeasureQty);
			}

			if (prescriptionQty >= 0) {

				int rQty = 0;
				if (prescriptionQtyp < 0) {
					rQty = prescriptionQty * -1;

				}

				else {
					rQty = prescriptionQty;
				}

				if (rQty == 0 && ct < 1) {
					prescriptionMeasurements.add(this
							.getNewPresrciptionMeasurement2(
									AppConfig.UNIT_OF_MEASURE_UNIT, rQty));
				}

				if (rQty > 0)

				{
					prescriptionMeasurements.add(this
							.getNewPresrciptionMeasurement2(
									AppConfig.UNIT_OF_MEASURE_UNIT, rQty));
				}

			}

		}

		return prescriptionMeasurements;

	}
	*/
	private StockMeasurement getNewPresrciptionMeasurement2(
			int unitOfMeasureId, int unitOfMeasureQty) {
		StockMeasurement prescriptionMeasurement = new StockMeasurement();
		// GlobalUnitofMeasure g = new GlobalUnitofMeasure();
		// GlobalItemUnitofMeasureVw g = new GlobalItemUnitofMeasureVw();

		GlobalItemUnitofMeasureVw g = globalItemUnitbo
				.getGlobalItemUnitofMeasureByIdvw(unitOfMeasureId);
		System.out.print("gggg" + g.getMeasurename());

		prescriptionMeasurement.setMeasurename(g.getMeasurename());
		prescriptionMeasurement.setQuantity(unitOfMeasureQty);

		prescriptionMeasurement.setCreatedBy(this.userIdentity.getUsername());
		prescriptionMeasurement.setCreatedDate(new Date());
		prescriptionMeasurement.setPrescribedststatus(this.prescribedstatusBo
				.getPrescribedstatusById(1));

		// prescriptionMeasurements.add(prescriptionMeasurement);
		return prescriptionMeasurement;
	}

	public Boolean checkunit(Integer id) {

		if (userIdentity.getStaffregistration().getHrunitcategory() == null)

		{
			return false;

		} else

		{
			if (userIdentity.getStaffregistration().getHrunitcategory()
					.getPoint().getId() != id)

			{

				return false;

			}
		}
		return true;

	}

	public Boolean checkCurrentunit(Integer id) {

		if (userIdentity.getCurrentUnit() == null)

		{
			System.out.print("I catch am here unit is null");
			return false;

		}

		if (userIdentity.getCurrentUnit().getPoint() == null)

		{
			System.out.print("I catch am here point is null");
			return false;
		}

		/*
		 * if (userIdentity.getCurrentUnit().getPoint().getId() != id) {
		 * System.out.print("I catch am here pointID is null");
		 * 
		 * return false; }
		 */

		System.out.print("I no catch am");
		return true;

	}

	public Set<StockMeasurement> convertunitstomeasure(int currentBalance, GlobalItem globalItem) {
		// TODO Auto-generated method stub
		return null;
	}

}