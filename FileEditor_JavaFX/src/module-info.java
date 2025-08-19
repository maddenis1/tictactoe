/**
 * 
 */
/**
 * 
 */
module FileEditor_JavaFX {
    requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens appfx to javafx.base,javafx.controls, javafx.graphics;
}
