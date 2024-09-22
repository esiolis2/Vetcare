package au.edu.rmit.sept.webapp.models;

public class ClinicReasons {

    private int id;
    private String serviceName;

    public ClinicReasons(int id, String serviceName){
        this.id = id;
        this.serviceName = serviceName;
    }

    public ClinicReasons(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
