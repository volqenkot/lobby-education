package io.volqe.education.utils;

import net.md_5.bungee.api.ChatColor;

public class GradientUtils {

    // Methode für Farbverläufe
    public static String applyGradient(String text, ChatColor startColor, ChatColor endColor) {
        StringBuilder result = new StringBuilder();
        int length = text.length();

        // Farbe RGB von Start und Ende
        int[] startRGB = {startColor.getColor().getRed(), startColor.getColor().getGreen(), startColor.getColor().getBlue()};
        int[] endRGB = {endColor.getColor().getRed(), endColor.getColor().getGreen(), endColor.getColor().getBlue()};

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            int red = (int) (startRGB[0] + ratio * (endRGB[0] - startRGB[0]));
            int green = (int) (startRGB[1] + ratio * (endRGB[1] - startRGB[1]));
            int blue = (int) (startRGB[2] + ratio * (endRGB[2] - startRGB[2]));
            ChatColor color = ChatColor.of(new java.awt.Color(red, green, blue));

            result.append(color).append(text.charAt(i));
        }
        return result.toString();
    }

}
