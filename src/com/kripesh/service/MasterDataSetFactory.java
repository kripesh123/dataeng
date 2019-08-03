package com.kripesh.service;

import com.kripesh.utils.Dictionary;

/**
 * @author kripesh
 */
public class MasterDataSetFactory {

    /**
     * Return the data generation and data update object according to param;
     * @param operation Input operation
     * @return Operation on data set
     */
    public MasterDataSet getOperation(String operation){
        if (null == operation || operation.isEmpty()){
            return null;
        }
        if(operation.equals(Dictionary.DATA_GENERATION)){
            return new DataGeneration();
        }else if (operation.equals(Dictionary.DATA_UPDATE)){
            return new DataUpdate();
        }
        return null;
    }
}
