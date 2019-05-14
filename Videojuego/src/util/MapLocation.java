package util;

public class MapLocation {
	
	/*
	 * Attributes
	 */
	private int mapId;
	private int squareId;
	
	
	/*
	 * Constructors
	 */
	public MapLocation() {}
	
	public MapLocation(final int mapId, final int squareId) {
		this.mapId = mapId;
		this.squareId = squareId;
	}
	
	
	/*
	 * MapId
	 */
	public int getMapId() { return mapId; }
	
	public void setMapId(final int mapId) { this.mapId = mapId; }
	
	
	/*
	 * SquareId
	 */
	public int getSquareId() { return squareId; }
	
	public void setSquareId(final int squareId) { this.squareId = squareId; }
	
	
	
	public void updateLocation(final int direction, int mapCount) {
		squareId += direction;
		
		if (squareId > 2) {
			squareId = 0;
			mapId = ++mapId == mapCount ? 0 : mapId;
		} else if (squareId < 0) {
			squareId = 2;
			mapId = --mapId < 0 ? --mapCount : mapId;
		}
	}
	
}