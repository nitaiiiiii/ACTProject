package com.ci.act.databinding;

import androidx.databinding.MergedDataBinderMapper;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
    DataBinderMapperImpl() {
        addMapper(new com.ci.act.DataBinderMapperImpl());
    }
}
