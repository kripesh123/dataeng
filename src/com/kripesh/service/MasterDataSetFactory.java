package com.kripesh.service;

import com.kripesh.service.DataGeneration;
import com.kripesh.service.DataUpdate;
import com.kripesh.service.MasterDataSet;
import com.kripesh.utils.Dictionary;

public class MasterDataSetFactory {

    public MasterDataSet getOperation(String s){
        if (null == s){
            return null;
        }
        if(s.equals(Dictionary.DATA_GENERATION)){
            return new DataGeneration();
        }else if (s.equals(Dictionary.DATA_UPDATE)){
            return new DataUpdate();
        }
        return null;
    }
}
