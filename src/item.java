/*File : item.java */
/*Author : 13512093 - Jonathan Sudibya */

import java.util.Vector;

public class item {
	private class i2
	{
		public int X;
		public int Y;
	}
	/* Attribute */
	private float waktu;
	
	private enum jenis {lemari,sabun,jebakan,anjing}
	
	public Vector<i2> posisi; 
	
	public Vector<i2> ukuran;
	
	/* Constructor */
	item()
	{
		posisi = new Vector<i2>(4,2);//Initial size = 4; can be increased by 2
		ukuran = new Vector<i2>(4,2);
	}
	/* method */
	public void Update()
	{
		
	}
        
        public void setWaktu(float _waktu)
        {
            waktu = _waktu;
        }
        
        public float getWaktu()
        {
            return waktu;
        }
        
}

