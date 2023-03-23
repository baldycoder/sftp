package SFTP.bean;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/14 09:21
 * @description：多线程传参bean
 * @modified By：`
 * @version: 1.0
 */

public class ThreadParamBean {
    private int id;
    private String fileName;
    private String fileSeparator;
    private String dataBaseType;
    private String tableName;
    private String status;
    private String beanName;
    private String fieldNames;
    private String mapperName;
    private String remoteUrl;
    private String loadScript;
    private String partitionFlag;
    private String partitionPeriod;
    private String partitionUnit;
    private String partitionMaxValue;
    private String createDate;
    private String createUser;
    private String updDate;
    private String udpUser;

    public ThreadParamBean(int id, String fileName, String fileSeparator, String dataBaseType, String tableName, String status, String beanName, String fieldNames, String mapperName, String remoteUrl, String loadScript, String partitionFlag, String partitionPeriod, String partitionUnit, String partitionMaxValue, String createDate, String createUser, String updDate, String udpUser) {
        this.id = id;
        this.fileName = fileName;
        this.fileSeparator = fileSeparator;
        this.dataBaseType = dataBaseType;
        this.tableName = tableName;
        this.status = status;
        this.beanName = beanName;
        this.fieldNames = fieldNames;
        this.mapperName = mapperName;
        this.remoteUrl = remoteUrl;
        this.loadScript = loadScript;
        this.partitionFlag = partitionFlag;
        this.partitionPeriod = partitionPeriod;
        this.partitionUnit = partitionUnit;
        this.partitionMaxValue = partitionMaxValue;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updDate = updDate;
        this.udpUser = udpUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public void setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getLoadScript() {
        return loadScript;
    }

    public void setLoadScript(String loadScript) {
        this.loadScript = loadScript;
    }

    public String getPartitionFlag() {
        return partitionFlag;
    }

    public void setPartitionFlag(String partitionFlag) {
        this.partitionFlag = partitionFlag;
    }

    public String getPartitionPeriod() {
        return partitionPeriod;
    }

    public void setPartitionPeriod(String partitionPeriod) {
        this.partitionPeriod = partitionPeriod;
    }

    public String getPartitionUnit() {
        return partitionUnit;
    }

    public void setPartitionUnit(String partitionUnit) {
        this.partitionUnit = partitionUnit;
    }

    public String getPartitionMaxValue() {
        return partitionMaxValue;
    }

    public void setPartitionMaxValue(String partitionMaxValue) {
        this.partitionMaxValue = partitionMaxValue;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdDate() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getUdpUser() {
        return udpUser;
    }

    public void setUdpUser(String udpUser) {
        this.udpUser = udpUser;
    }
}
