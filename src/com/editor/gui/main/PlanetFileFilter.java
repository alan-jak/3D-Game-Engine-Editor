package com.editor.gui.main;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.filechooser.FileFilter;

public class PlanetFileFilter extends FileFilter {
  private Hashtable<String, PlanetFileFilter> filters = null;

  private String description = "Planet Scene File";

  private String fullDescription = null;

  private boolean useExtensionsInDescription = true;

  public PlanetFileFilter() {
    this.filters = new Hashtable<String, PlanetFileFilter>();
  }

  public PlanetFileFilter(String extension) {
    this(extension, null);
  }

  public PlanetFileFilter(String extension, String description) {
    this();
    if (extension != null)
      addExtension(extension);
    if (description != null)
      setDescription(description);
  }

  public PlanetFileFilter(String[] filters) {
    this(filters, null);
  }

  public PlanetFileFilter(String[] filters, String description) {
    this();
    for (int i = 0; i < filters.length; i++) {
      addExtension(filters[i]);
    }
    if (description != null)
      setDescription(description);
  }

  public boolean accept(File f) {
    if (f != null) {
      if (f.isDirectory()) {
        return true;
      }
      String extension = getExtension(f);
      if (extension != null && filters.get(getExtension(f)) != null) {
        return true;
      }
    }
    return false;
  }

  public String getExtension(File f) {
    if (f != null) {
      String filename = f.getName();
      int i = filename.lastIndexOf('.');
      if (i > 0 && i < filename.length() - 1) {
        return filename.substring(i + 1).toLowerCase();
      }
    }
    return null;
  }

  public void addExtension(String extension) {
    if (filters == null) {
      filters = new Hashtable<String, PlanetFileFilter>(5);
    }
    filters.put(extension.toLowerCase(), this);
    fullDescription = null;
  }

  public String getDescription() {
    if (fullDescription == null) {
      if (description == null || isExtensionListInDescription()) {
        fullDescription = description == null ? "(" : description + " (";
        Enumeration<String> extensions = filters.keys();
        if (extensions != null) {
          fullDescription += "." + (String) extensions.nextElement();
          while (extensions.hasMoreElements()) {
            fullDescription += ", " + (String) extensions.nextElement();
          }
        }
        fullDescription += ")";
      } else {
        fullDescription = description;
      }
    }
    return fullDescription;
  }

  public void setDescription(String description) {
    this.description = description;
    fullDescription = null;
  }

  public void setExtensionListInDescription(boolean b) {
    useExtensionsInDescription = b;
    fullDescription = null;
  }

  public boolean isExtensionListInDescription() {
    return useExtensionsInDescription;
  }
}