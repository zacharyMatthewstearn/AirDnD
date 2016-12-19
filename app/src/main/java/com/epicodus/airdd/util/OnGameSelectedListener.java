package com.epicodus.airdd.util;

import com.epicodus.airdd.models.Game;

import java.util.List;

public interface OnGameSelectedListener {
    void OnGameSelected(Integer position, List<Game> games);
}
