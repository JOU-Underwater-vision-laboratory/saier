package com.hhit.saier.domain;

/**
 * Medicine entity.
 * @author: ray
 *
 */

public class MedicineDO implements java.io.Serializable {

	/**国药准字号： 国药准字Z45021599*/
	private String licensenumber;
	/**药品名： 桂林西瓜霜*/
	private String medicinename;
	/**有效成分： 西瓜霜、锻硼砂、黄柏、黄连、山豆根、射干、浙贝母、青黛、冰片、大黄、木汉果（炭）黄芩、甘草、薄荷脑*/
	private String activeingredient;
	/**药物特征： 本品为灰黄绿色的粉末；气香，味咸、微苦而辛凉*/
	private String medcharacter;
	/**剂量 ： 每瓶装2.5克*/
	private String dose;
	/** 药量(医嘱)： 喷（吹）敷患处，一次适量，一日数次。
	 重症者兼服，一次1～2g，次数视情况而定。*/
	private String dosage;
	/**禁忌： 忌烟酒、辛辣、鱼腥食物。*/
	private String contraindication;
	/** 迹象： 口腔溃疡*/
	private String indication;
	/** 药量（说明书说明）：喷（吹）敷患处，一次适量，一日数次。*/
	private String dosagefromdoc;
	/**副作用： 尚不明确*/
	private String untowardeffect;
	/**药物相互影响： 与其他药物可能发生相互作用,请询问医师 */
	private String druginteraction;
	/**有效期： 36个月*/
	private String periodvalidity;
	/** 生产厂家 : 桂林三金药业股份有限公司 */
	private String manufacturer;
	/** 储藏条件: 密闭，防潮。*/
	private String storageconditions;
	/** 条形码*/
	private String barCode;

	public MedicineDO() {
	}

    public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	public String getMedicinename() {
		return medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	public String getActiveingredient() {
		return activeingredient;
	}

	public void setActiveingredient(String activeingredient) {
		this.activeingredient = activeingredient;
	}

	public String getMedcharacter() {
		return medcharacter;
	}

	public void setMedcharacter(String medcharacter) {
		this.medcharacter = medcharacter;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getContraindication() {
		return contraindication;
	}

	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getDosagefromdoc() {
		return dosagefromdoc;
	}

	public void setDosagefromdoc(String dosagefromdoc) {
		this.dosagefromdoc = dosagefromdoc;
	}

	public String getUntowardeffect() {
		return untowardeffect;
	}

	public void setUntowardeffect(String untowardeffect) {
		this.untowardeffect = untowardeffect;
	}

	public String getDruginteraction() {
		return druginteraction;
	}

	public void setDruginteraction(String druginteraction) {
		this.druginteraction = druginteraction;
	}

	public String getPeriodvalidity() {
		return periodvalidity;
	}

	public void setPeriodvalidity(String periodvalidity) {
		this.periodvalidity = periodvalidity;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getStorageconditions() {
		return storageconditions;
	}

	public void setStorageconditions(String storageconditions) {
		this.storageconditions = storageconditions;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Override
	public String toString() {
		return "MedicineDO{" +
				"licensenumber='" + licensenumber + '\'' +
				", medicinename='" + medicinename + '\'' +
				", activeingredient='" + activeingredient + '\'' +
				", medcharacter='" + medcharacter + '\'' +
				", dose='" + dose + '\'' +
				", dosage='" + dosage + '\'' +
				", contraindication='" + contraindication + '\'' +
				", indication='" + indication + '\'' +
				", dosagefromdoc='" + dosagefromdoc + '\'' +
				", untowardeffect='" + untowardeffect + '\'' +
				", druginteraction='" + druginteraction + '\'' +
				", periodvalidity='" + periodvalidity + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", storageconditions='" + storageconditions + '\'' +
				", barCode='" + barCode + '\'' +
				'}';
	}

    public MedicineDO build(MedicineMini medicineMini) {
		this.setBarCode(medicineMini.getBarcode());
		this.setManufacturer(medicineMini.getManufacturer());
		this.setMedicinename(medicineMini.getName());
		return this;
    }

	public MedicineDO buildEmpty(MedicineDO medicine) {
		this.setDruginteraction("");
		this.setDosage("");
		this.setActiveingredient("");
		this.setLicensenumber("");
		this.setManufacturer("");
		this.setContraindication("");
		this.setBarCode("");
		this.setDosagefromdoc("");
		this.setIndication("");
		this.setUntowardeffect("");
		return this;
	}
}