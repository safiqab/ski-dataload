package com.dish.scm.ski.skidataload.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuAttributeReferenceVM {

	private List<FeatureTypeRefVM> lstFeatureTypeRefVM;
	private List<ConfigTypeRefVM> lstConfigTypeRefVM;
	private List<ManufacturerRefVM> lstManufacturerRefVM;
	private List<SupplierRefVM> lstSupplierRefVM;
	private List<SupplierConfigRefVM> lstSupplierConfigRefVM;
	private List<EquipIdRefVM> lstEquipIdRefVM;
	private List<SimTypeRefVM> lstSimTypeRefVM;
	private List<SimFormRefVM> lstSimFormRefVM;
	private List<PackageTypeRefVM> lstPackageTypeRefVM;
	private List<SkuTypeRefVM> lstSkuTypeRefVM;
	private List<UnitOfMeasRefVM> lstUnitOfMeasRefVM;
	private List<ColorRefVM> lstColorRefVM;
	private List<ColorHexRefVM> lstColorHexRefVM;
	private List<DeptIdRefVM> lstDeptIdRefVM;
	private List<SubDeptIdRefVM> lstSubDeptIdRefVM;
	private List<OsTypeRefVM> lstOsTypeRefVM;
	private List<SerialTypeRefVM> lstSerialTypeRefVM;
	private List<EquipTypeRefVM> lstEquipTypeRefVM;
	private List<EquipSubTypeRefVM> lstEquipsubTypeRefVM;
	private List<EquipSubCatRefVM> lstEquipSubCatRefVM;
	private List<LangCodeRefVM> lstLangcodeRefVM;
	private List<BrandCodeRefVM> lstBrandcodeRefVM;
	private List<LocationRefVM> lstLocationRefVM;
	private List<ChannelRefVM> lstChannelRefVM;
	private List<UrlTypeRefVM> lstUrlTypeRefVM;
	private List<OwnershipCodeRefVM> lstOwnershipcodeRefVM;
	private List<ManufacturerConfigRefVM> lstManufacturerConfigRefVM;
	private List<OwnershipCodeMappingVM> lstOwnershipcodeMappingVM;
	private List<SkuOwnerRefVM> lstSkuOwnerRefVM;
	private List<NetworkCodeRefVM> lstNetworkCodeRefVM;
	private List<ComponentTypeRefVM> lstComponentTypeRefVM;
	private List<ProfileTypeRefVM> lstProfileTypeRefVM;
	private List<TransactionTypeRefVM> lstTransactionTypeRefVM;
	private List<DispositionCodeRefVM> lstDispositioncodeRefVM;
	private List<ShipmentStatusRefVM> lstShipmentstatusRefVM;
	private List<UnlockStatusRefVM> lstUnlockStatusRefVM;
	private List<CountryOfOrginRefVM> lstCountryOfOrginRefVM;

}
