/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2012 levon
 *
 *  This file author is levon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.features.map;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.freeplane.core.extension.ExtensionContainer;
import org.freeplane.core.extension.IExtension;
import org.freeplane.core.extension.SmallExtensionMap;
import org.freeplane.core.util.HtmlUtils;
import org.freeplane.core.util.XmlUtils;
import org.freeplane.features.filter.FilterInfo;
import org.freeplane.features.icon.MindIcon;

/**
 * @author Lev Lazar
 * Mar 3, 2012
 */
public class ContentModel {
	private MapModel map = null;
	private final ExtensionContainer extensionContainer;
	final private FilterInfo filterInfo = new FilterInfo();
	private HistoryInformationModel historyInformation = null;
	final private NodeIconSetModel icons;
	private String id;
	private Object userObject = null;
	private String xmlText = null;
	private final Collection<INodeView> views = null;

	public ContentModel(final Object userObject, final MapModel map) {
		setUserObject(userObject);
		this.map = map;
		extensionContainer = new ExtensionContainer(new SmallExtensionMap());
		icons = new NodeIconSetModel();
	}

	public void addExtension(final IExtension extension) {
		extensionContainer.addExtension(extension);
	}

	public IExtension putExtension(final IExtension extension) {
		return extensionContainer.putExtension(extension);
	}

	public void addIcon(final MindIcon icon) {
		icons.addIcon(icon);
		if (map != null) {
			map.getIconRegistry().addIcon(icon);
		}
	}

	public void addIcon(final MindIcon icon, final int position) {
		icons.addIcon(icon, position);
		getMap().getIconRegistry().addIcon(icon);
	}

	public boolean containsExtension(final Class<? extends IExtension> clazz) {
		return extensionContainer.containsExtension(clazz);
	}

	public String createID() {
		if (id == null) {
			id = map.registryContent(this);
		}
		return id;
	}

	public HistoryInformationModel getHistoryInformation() {
		return historyInformation;
	}

	public String getID() {
		return id;
	}

	public <T extends IExtension> T getExtension(final Class<T> clazz) {
		return extensionContainer.getExtension(clazz);
	}

	public Map<Class<? extends IExtension>, IExtension> getExtensions() {
		return extensionContainer.getExtensions();
	}

	public FilterInfo getFilterInfo() {
		return filterInfo;
	}

	public MindIcon getIcon(final int position) {
		return icons.getIcon(position);
	}

	public List<MindIcon> getIcons() {
		return icons.getIcons();
	}

	public MapModel getMap() {
		return map;
	}

	public String getText() {
		String string = "";
		if (userObject != null) {
			string = userObject.toString();
		}
		return string;
	}

	public Object getUserObject() {
		return userObject;
	}

	public boolean hasID() {
		return id != null;
	}

	public <T extends IExtension> T removeExtension(final Class<T> clazz) {
		return extensionContainer.removeExtension(clazz);
	}

	public boolean removeExtension(final IExtension extension) {
		return extensionContainer.removeExtension(extension);
	}

	/**
	 * remove last icon
	 * 
	 * @return the number of remaining icons.
	 */
	public int removeIcon() {
		return icons.removeIcon();
	}

	/**
	 * @param remove icons with given position
	 *  
	 * @return the number of remaining icons
	 */
	public int removeIcon(final int position) {
		return icons.removeIcon(position);
	}

	public void setMap(final MapModel map) {
		this.map = map;
	}

	public void setHistoryInformation(final HistoryInformationModel historyInformation) {
		this.historyInformation = historyInformation;
	}

	public void setID(final String value) {
		id = value;
		getMap().registryID(value, this);
	}

	public final void setText(final String text) {
		userObject = XmlUtils.makeValidXml(text);
		xmlText = HtmlUtils.toXhtml(text);
		if (xmlText != null && !xmlText.startsWith("<")) {
			userObject = " " + text;
			xmlText = null;
		}
	}

	public final void setUserObject(final Object data) {
		if (data instanceof String) {
			setText(data.toString());
			return;
		}
		userObject = data;
		xmlText = null;
	}

	public final void setXmlText(final String pXmlText) {
		xmlText = XmlUtils.makeValidXml(pXmlText);
		userObject = HtmlUtils.toHtml(xmlText);
	}
}
