/**
 * 
 */
/**
 * 
 */
module Tictactoe {	
	
    requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens tiktaktoe to javafx.base,javafx.controls, javafx.graphics;
}
