package maps;

import java.util.ArrayList;


public class MapManager {

	/*
	 * Attributes
	 */
	private ArrayList<Map> mapList;
	private int selectedMapIndex;

	/*
	 * Constructors
	 */
	public MapManager() {
		mapList = new ArrayList<Map>();
	}

	public MapManager(final ArrayList<Map> mapList, final int selectedMapIndex) {
		this.mapList = mapList;
		this.selectedMapIndex = selectedMapIndex;
	}

	public MapManager(final ArrayList<Map> mapList) {
		this.mapList = mapList;
	}

	/*
	 * Getters
	 */
	public ArrayList<Map> getMapList() {
		return mapList;
	}

	public int getSelectedMapIndex() {
		return selectedMapIndex;
	}

	public Map getMapByIndex(final int index) {
		return mapList.get(index);
	}

	public Map getSelectedMap() {
		return mapList.get(selectedMapIndex);
	}

	/*
	 * Setters
	 */
	public void setMapList(final ArrayList<Map> mapList) {
		this.mapList = mapList;
	}

	public void setSelectedMapIndex(final int selectedMapIndex) {
		this.selectedMapIndex = selectedMapIndex;
	}

	public void setAddMapToArray(final Map map) {
		mapList.add(map);
	}

}
