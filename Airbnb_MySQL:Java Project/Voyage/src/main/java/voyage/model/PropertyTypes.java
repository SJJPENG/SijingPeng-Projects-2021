package voyage.model;

public class PropertyTypes {
    protected int propertyId;
    protected String propertyType;

    public PropertyTypes(int propertyId, String propertyType) {
        this.propertyId = propertyId;
        this.propertyType = propertyType;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

	@Override
	public String toString() {
		return "PropertyTypes [propertyId=" + propertyId + ", propertyType=" + propertyType + "]";
	}

	
}
