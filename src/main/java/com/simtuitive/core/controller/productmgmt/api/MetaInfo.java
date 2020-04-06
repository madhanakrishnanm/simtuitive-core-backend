package com.simtuitive.core.controller.productmgmt.api;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents meta section of JSON API output
 * @author Surath Patnaik
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaInfo {

    private Map<String, Object> metaMap;

    /**
     * @return the metaMap
     */
    public Map<String, Object> getMetaMap() {
        return metaMap;
    }

    /**
     * @param metaMap
     *            the metaMap to set
     */
    public void setMetaMap(HashMap<String, Object> metaMap) {
        this.metaMap = metaMap;
    }

    /**
     * 
     * @param key
     * @param val
     */
    public void addMetaDetail(String key, Object val) {
        if (metaMap == null) {
            metaMap = new HashMap();
        }

        metaMap.put(key, val);
    }
}
