package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "certificationVersion")
@XmlEnum
public enum CertificationVersion {

	@XmlEnumValue("V1")
	V1("V1"),
	@XmlEnumValue("V2")
	V2("V2"),
	@XmlEnumValue("V3")
	V3("V3");
	
	private String value;
	
	CertificationVersion(String value) {
		this.value = value;
	}
	
	public static CertificationVersion fromValue(String value) {
		for(CertificationVersion layerStatus : CertificationVersion.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
