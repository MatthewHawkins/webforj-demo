package org.example.utils.renderers;

import org.example.model.StockData;

import com.webforj.component.table.renderer.Renderer;

public class CompanyRenderer extends Renderer<StockData> {
    @Override
    public String build() {
      return /* html */"""
          <%
          const domain = cell.row.getValue('Domain');
          const name = cell.row.getValue('Name');
          const ticker = cell.row.getValue('Ticker');
          %>
          <div part='company'>
            <img
              part='company-img'
              src='https://img.logo.dev/<%= domain %>?token=pk_MznEkfK0TnePSpUuoi-gIg' />
            <div part="company-name">
              <%= name %>
              <div part="company-ticker">
                <%= ticker %>
              </div>
            </div>
          </div>
          
          """;
    }
  }
