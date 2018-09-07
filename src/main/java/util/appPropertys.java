/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author vasil
 */
public class appPropertys {

    private final Logger log = Logger.getLogger(appPropertys.class);
    private Properties props = new Properties();
    private File fileName;

    public appPropertys(File filename) {
        log.debug("appPropertys = " + filename);
        this.fileName = filename;
        this.load();
    }

    private void load() {
        try {
            log.debug("load()");
            FileInputStream input = new FileInputStream(this.fileName);
            props.load(input);
            input.close();
        } catch (Exception ignore) {
            log.error(ignore);
        }
    }

    public void save() {
        try {
            log.debug("save()");
            FileOutputStream output = new FileOutputStream(this.fileName);
            props.store(output, "Saved settings");
            output.close();
        } catch (Exception ignore) {
            log.error(ignore);
        }
    }

    public Properties getProps() {
        return props;
    }
    
    
}
