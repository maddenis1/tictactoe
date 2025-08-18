/**
 * 
 */
/**
 * 
 */
module Denisprojekt  {
    requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens spielo to javafx.base,javafx.controls, javafx.graphics;
}