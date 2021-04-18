package com.example.getcrispnews.utils

object ColorPicker {
    var colors = arrayOf( "#41B3A3", "#64485C", "#DAAD86",  "#8D8741", "#BC986A",  	"#97CAEF",
        "#8EE4AF", 	"#83677B", 	"#E7717D", "#ffbf00", "#E27D60", 	"#83677B", "#C38D9E", 	"#8EE4AF", "#E7717D", "#B1A296")

    var colorIndex = 0
    public
    fun getColors(): String
    {
        return colors[ (colorIndex++ % colors.size) ]
    }

}