package Components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicHTML;

import kux.glass.swing.CGlassButton;


public class ColoredButton extends CGlassButton {
   private static final long serialVersionUID = 1L;

   private Color foregroundColor = null;
   private Color disabledForegroundColor = null;
   private boolean bIsHTMLText = false;
   private static final int WIDTH = 26; //!< default button width
   private static final int HEIGHT = 26; //!< default button height

   private static final String FONTNAME = Font.SANS_SERIF;
   private static final int FONTSIZE = 15;
   private static Random random = new Random();

   public ColoredButton () {
      disabledForegroundColor = UIManager.getColor("textInactiveText");
   }

   @Override
   public void setEnabled (boolean b) {
      super.setEnabled(b);
      if (bIsHTMLText) {
         if (b) {
            if (foregroundColor != null) {
               super.setForeground(foregroundColor);
            }
         }
         else {
            if (disabledForegroundColor != null) {
               foregroundColor = getForeground();
               super.setForeground(disabledForegroundColor);
            }
         }
      }
   }

   @Override
   public void setText (String text) {
      super.setText(text);
      bIsHTMLText = BasicHTML.isHTMLString(text);
      setEnabled(isEnabled());
   }

   @Override
   public void setForeground (Color c) {
      if ( !bIsHTMLText || isEnabled()) {
         super.setForeground(c);
      }
      foregroundColor = c;
   }

   public static ColoredButton GetOkButton (String strName, String strTooltip) {
      final ColoredButton buttonOk = new ColoredButton();

      setProperties(buttonOk, strName, strTooltip);
      buttonOk.setForeground(new Color(0, 100, 0));
      buttonOk.setLightColor(new Color(0, 180, 0));

      return buttonOk;
   }

   public static ColoredButton GetCancelButton (final String strName, final String strTooltip) {
      final ColoredButton buttonCancel = new ColoredButton();

      setProperties(buttonCancel, strName, strTooltip);
      buttonCancel.setForeground(new Color(100, 0, 0));
      buttonCancel.setLightColor(new Color(180, 0, 0));

      return buttonCancel;
   }
   
   public static ColoredButton GetRandomButton(final String strName, final String strTooltip) {
	      final ColoredButton buttonCancel = new ColoredButton();

	      setProperties(buttonCancel, strName, strTooltip);
	      buttonCancel.setForeground(new Color(100, 0, 0));
	      buttonCancel.setLightColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

	      return buttonCancel;
	   }

   private static void setProperties (final ColoredButton button, final String strName, final String strTooltip) {
      //button.setBackground(CAppData.AppColor());
      button.setFont(new Font(FONTNAME, Font.PLAIN, FONTSIZE));

      if (strName == null || strName.isEmpty()) {
         button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      }
      else {
         button.setPreferredSize(new Dimension(100, HEIGHT));
         button.setText(strName);
      }
      if (strTooltip != null && !strTooltip.isEmpty()) {
         button.setToolTipText(strTooltip);
      }
      button.setOpaque(false);
      button.setSelectable(false);
      button.setCheckable(false);
      button.setFlat(false);
   }
   

}
