package com.EngBassemOs.foodflow.network.area;

import com.EngBassemOs.foodflow.model.Area;

import java.util.List;

public interface AreaNetworkDelegate {
    public void onSuccessResult(List<Area> area);
    public void onFailureResult(String error);
}
