package Components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JSeparator;

public class CSeparator extends JSeparator{
	private static final long serialVersionUID = 1L;

	public CSeparator(){
		super(JSeparator.VERTICAL);
		setPreferredSize(new Dimension(1,17));
		setBorder(BorderFactory.createRaisedSoftBevelBorder());
	}
}
