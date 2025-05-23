package org.example.utils;

import org.example.data.SalesEntry;

import com.webforj.component.table.renderer.Renderer;

public class CompanyRenderer extends Renderer<SalesEntry> {
  @Override
  public String build() {
    return /* html */"""
      <% 
        const logo = cell.row.getValue("Company");
        const logo = cell.row.getValue("Product");
        const logo = cell.row.getValue("SerialNumber");
        console.log(logo);
      %>
        <div part='product'>
          <div part='product-icon'>
            <img src="https://cdn.simpleicons.org/<%=logo %>/666666" alt="Logo" />
          </div>
          <div part='product-info'>
            <div part='product-name'>
            </div>
            <div part='product-serial'>
            </div>
          </div>
        </div>
        """;
  }
}
