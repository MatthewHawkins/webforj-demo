package org.example.utils.renderers;

import org.example.model.StockData;

import com.webforj.component.table.renderer.Renderer;

public class PriceRenderer extends Renderer<StockData> {
    @Override
    public String build() {
      return /* html */"""
          <%
          const price = cell.row.getValue('Price');
          const isUp = cell.row.getValue('IsUp');
          %>
          <div part='price'>
              <div part='price-<%= isUp=="true" ? "high" : "low"  %>'>
                <%= price %>
              </div>
          </div>
          """;
    }
  }