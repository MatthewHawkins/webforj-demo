package org.example.utils;

import org.example.data.SalesEntry;

import com.webforj.component.table.renderer.Renderer;

public class HighLowRenderer extends Renderer<SalesEntry> {
  @Override
  public String build() {
    return /* html */"""
        <span part='badge badge-<%= cell.value == "HIGH" ? "high" : cell.value == "LOW" ? "low" : "medium" %>'>
        </span>
        """;
  }
}