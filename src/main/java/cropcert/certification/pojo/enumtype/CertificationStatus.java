package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "certificationStatus")
@XmlEnum
public enum CertificationStatus {

	@XmlEnumValue("C1")
	C1("C1"),
	@XmlEnumValue("C2")
	C2("C2"),
	@XmlEnumValue("C3")
	C3("C3");
	
	private String value;
	
	CertificationStatus(String value) {
		this.value = value;
	}
	
	public static CertificationStatus fromValue(String value) {
		for(CertificationStatus layerStatus : CertificationStatus.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
