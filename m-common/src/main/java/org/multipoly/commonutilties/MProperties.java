package org.multipoly.commonutilties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by chris on 2015/09/23.
 */
public class MProperties {

    private PropertiesConfiguration config;
    public final static MProperties INSTANCE = new MProperties();
    private final Logger logger;
    private int commitCount;

    private MProperties() {
        super();
        this.logger = LoggerFactory.getLogger(MProperties.class);
        ;
        try {
            this.config = new PropertiesConfiguration("../resources/cm.properties");
            this.config.setReloadingStrategy(new FileChangedReloadingStrategy());
            this.logger.info("Loading cm.properties from ./../resources/cm.properties");
        } catch (ConfigurationException e) {
            try {
                this.config = new PropertiesConfiguration("cm.properties");
                this.logger.info("Loading cm.properties from classpath");
            } catch (ConfigurationException e1) {
                //for jetty from ide
                try {
                    this.config = new PropertiesConfiguration("cm-common/src/assembly/properties/cm.properties");
                    this.logger.info("Loading cm.properties from cm-common/src/assembly/properties/cm.properties");
                } catch (ConfigurationException e2) {
                    //for eclipse from the ide
                    try {
                        this.config = new PropertiesConfiguration("./../cm-common/src/assembly/properties/cm.properties");
                        this.logger.info("Loading cm.properties from ./../cm-common/src/assembly/properties/cm.properties");
                    } catch (ConfigurationException e3) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public PropertiesConfiguration getProperties() {
        return config;
    }

    public void setConfig(PropertiesConfiguration config) {
        this.config = config;
    }

    public Configuration subset(String s) {
        return this.config.subset(s);
    }

    public boolean isEmpty() {
        return this.config.isEmpty();
    }

    public boolean containsKey(String s) {
        return this.config.containsKey(s);
    }

    public void addProperty(String s, Object o) {
        this.config.addProperty(s, o);
    }

    public void setProperty(String s, Object o) {
        this.config.setProperty(s, o);
    }

    public void clearProperty(String s) {
        this.config.clearProperty(s);
    }

    public void clear() {
        this.config.clear();
    }

    public Object getProperty(String s) {
        return this.config.getProperty(s);
    }

    public Iterator getKeys(String s) {
        return this.config.getKeys(s);
    }

    public Iterator getKeys() {
        return this.config.getKeys();
    }


    public Properties getProperties(String s) {
        return this.config.getProperties(s);
    }

    public boolean getBoolean(String s) {
        return this.config.getBoolean(s);
    }

    public boolean getBoolean(String s, boolean b) {
        return this.config.getBoolean(s, b);
    }

    public Boolean getBoolean(String s, Boolean aBoolean) {
        return this.config.getBoolean(s, aBoolean);
    }

    public byte getByte(String s) {
        return this.config.getByte(s);
    }

    public byte getByte(String s, byte b) {
        return this.config.getByte(s, b);
    }

    public Byte getByte(String s, Byte aByte) {
        return this.config.getByte(s, aByte);
    }

    public double getDouble(String s) {
        return this.config.getDouble(s);
    }

    public double getDouble(String s, double v) {
        return this.config.getDouble(s, v);
    }

    public Double getDouble(String s, Double aDouble) {
        return this.config.getDouble(s, aDouble);
    }

    public float getFloat(String s) {
        return this.config.getFloat(s);
    }

    public float getFloat(String s, float v) {
        return this.config.getFloat(s, v);
    }

    public Float getFloat(String s, Float aFloat) {
        return this.config.getFloat(s, aFloat);
    }

    public int getInt(String s) {
        return this.config.getInt(s);
    }

    public int getInt(String s, int i) {
        return this.config.getInt(s, i);
    }

    public Integer getInteger(String s, Integer integer) {
        return this.config.getInteger(s, integer);
    }

    public long getLong(String s) {
        return this.config.getLong(s);
    }

    public long getLong(String s, long l) {
        return this.config.getLong(s, l);
    }

    public Long getLong(String s, Long aLong) {
        return this.config.getLong(s, aLong);
    }

    public short getShort(String s) {
        return this.config.getShort(s);
    }

    public short getShort(String s, short i) {
        return this.config.getShort(s, i);
    }

    public Short getShort(String s, Short aShort) {
        return this.config.getShort(s, aShort);
    }

    public BigDecimal getBigDecimal(String s) {
        return this.config.getBigDecimal(s);
    }

    public BigDecimal getBigDecimal(String s, BigDecimal bigDecimal) {
        return this.config.getBigDecimal(s, bigDecimal);
    }

    public BigInteger getBigInteger(String s) {
        return this.config.getBigInteger(s);
    }

    public BigInteger getBigInteger(String s, BigInteger bigInteger) {
        return this.config.getBigInteger(s, bigInteger);
    }

    public String getString(String s) {
        return this.config.getString(s);
    }

    public String getString(String s, String s2) {
        return this.config.getString(s, s2);
    }

    public String[] getStringArray(String s) {
        return this.config.getStringArray(s);
    }

    public List getList(String s) {
        return this.config.getList(s);
    }

    public List getList(String s, List list) {
        return this.config.getList(s, list);
    }

    public int getRIPConnectionTimeout() {
        return getInt("rip.connection.timeout.seconds");
    }

    public String getDBName() {
        return getString("db.name");
    }

    public String getDBUser() {
        return getString("db.user");
    }

    public String getDBPassword() {
        return getString("db.password");
    }

    public String getHibernateDialect() {
        return getString("hibernate.dialect");
    }

    public String getDbXaDatasourceClass() {
        return getString("db.xa.datasource.class");
    }

    public boolean shouldUpdateDbStructure() {
        return getBoolean("db.update", false);
    }

    public boolean isAuditActive() {
        return getBoolean("auditActive", false);
    }

    public String getDBConnectionUrl() {
        return getString("db.connection.url");
    }

    public String getDBPort() {
        return getString("db.port");
    }

    public String getDBHost() {
        return getString("db.host");
    }

    public boolean isParseEricssonXml() {
        return getBoolean("parse.ericsson_xml", true);
    }

    public boolean isParseNsnXml() {
        return getBoolean("parse.nsn_xml", true);
    }

    public int getRawToCsvThreadCount() {
        return getInt("rawtocsv.thread.count", 1);
    }

    public int getCsvToLoadThreadCount() {
        return getInt("csvtoload.thread.count", 1);
    }

    public int getDifferanceGuruThreadCount() {
        return getInt("differance.guru.thread.count", 1);
    }

    public boolean getEtlUseAvailableProcesses() {
        return getBoolean("etl.use.available.processes", true);
    }

    public String getDataRootDir() {
        return getString("data.root.dir");
    }

    public String getUtranXmlFormatVersion() {
        return getString("ericsson.specific.attributes.version", "EricssonSpecificAttributes.11.27");
    }

    public String getUtranFileFormateVersion() {
        return getString("utran.file.format.version", "32.615 V4.5");
    }

    public boolean isEtlDefinitionUpdate() {
        return getBoolean("etldefinition.update", true);
    }

    public List<String> getEricssonLteSubnetworkFilter() {
        return getList("ericsson.lte.subnetwork.filter");
    }

    public List<String> getEricssonUmtsSubnetworkFilter() {
        return getList("ericsson.umts.subnetwork.filter");
    }

    public List<String> getZteLteSubnetworkFilter() {
        return getList("zte.lte.subnetwork.filter");
    }

    public List<String> getZteUmtsSubnetworkFilter() {
        return getList("zte.umts.subnetwork.filter");
    }

    public boolean isEtlServer() {
        return getBoolean("isEtlServer", true);
    }

    public boolean getDropWorkspaceElementForeignKeys() {
        return getBoolean("db.drop.workspace.element.foreign.key", false);
    }

    public boolean getDropWorkspaceElementIndexes() {
        return getBoolean("db.drop.workspace.element.indexes", false);
    }

    public boolean getDropWorkspaceElementUniqueConstraint() {
        return getBoolean("db.drop.workspace.element.unique.constraint", false);
    }

    public String getAmosCommandPassword() {
        return getString("amos.node.password", "rnc");
    }

    public String[] getNsnCreationNetworkTypes() {
        return getStringArray("nsn.creation.network.types");
    }

    public String[] getNsnGsmVersionFilter() {
        return getStringArray("nsn.gsm.version.filter");
    }

    public String[] getNsnUmtsVersionFilter() {
        return getStringArray("nsn.umts.version.filter");
    }

    public String[] getNsnLteVersionFilter() {
        return getStringArray("nsn.lte.version.filter");
    }

    public String[] getAulGsmVersionExclusionFilter() {
        return getStringArray("aul.gsm.version.exclusion.filter");
    }

    public String[] getAulUmtsVersionExclusionFilter() {
        return getStringArray("aul.umts.version.exclusion.filter");
    }

    public String[] getAulLteVersionExclusionFilter() {
        return getStringArray("aul.lte.version.exclusion.filter");
    }

    public String[] getHuaweiLteObjectTypes() {
        return getStringArray("huawei_lte_object_types");
    }

    public int getAuditKeepMonths() {
        return getInt("audit.keep.months", 3);
    }

    public Object getNewUserEmailUrl() {
        return getString("newUserEmailUrl", "http://localhost:8080");
    }

    public String getLocalTestDir() {
        return getString("local.test.dir");
    }

    public String getCountryLatitude() {
        return getString("country.latitude", "-27.0");
    }

    public String getCountryLongitude() {
        return getString("country.longitude", "29.0");
    }

    public int getCmJettyPort() {
        return getInt("cmjetty.port", 8111);
    }

    public String getCmJettyHost() {
        return getString("cmjetty.host", "localhost");
    }

    public int getEtlServerPort() {
        return getInt("etlserver.port");
    }

    public String getGISFile() {
        return getString("gis.data.file");
    }

    public int getElementDeleteThreshold() {
        return getInt("element.delete.threshold");
    }

    public String getKeystoreDir() {
        return getString("keystore.dir", "/usr/share/rorotika/ssl");
    }

    public String getKeystorePass() {
        return getString("keystore.pass", "rorotika1");
    }

    public String getKeystoreName() {
        return getString("keystore.name", "netcm");
    }

    public String getCertAlias() {
        return getString("cert.alias", "netcm");
    }

    public String getCertCommonName() {
        return getString("cert.commonname", "localhost");
    }

    public String getReportExportLocalDir() {
        return getString("local.export.dir");
    }

    public String getReportExportRemoteDir() {
        return getString("remote.export.dir");
    }

    public int getCommitCount() {
        return getInt("commit.count", 10000);
    }

    public String getAboutVersion() {
        return getString("about.version");
    }

    public String getAboutSupport() {
        return getString("about.support");
    }
}
