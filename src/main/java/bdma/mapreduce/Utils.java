package bdma.mapreduce;

public class Utils {

    /*

	public void put(WineInfo w) {
		this.keyBuffer.append(w.getType());
		this.valueBuffer.append(w.getRegion()+","+w.getAlc()+","+w.getMAcid()+","+w.getAsh()+","+w.getAlcAsh()+
				","+w.getMgn()+","+w.getTPhenols()+","+w.getFlav()+","+w.getNonflavPhenols()+","+w.getProant()+","+
				w.getCol()+","+w.getHue()+","+w.getOd280od315()+","+w.getProline());
	}

     */
	private static final String[] attributes = new String[] {
		"type",
		"region",
		"alc",
		"m_acid",
		"ash",
		"alc_ash",
		"mgn",
		"t_phenols",
		"flav",
		"nonflav_phenols",
		"proant",
		"col",
		"hue",
		"od280/od315",
		"proline"
	};
	
	public static String getAttribute(String[] row, String attribute) {
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].equals(attribute)) {
				return row[i];
			}
		}
		return null;
	}
	
}
