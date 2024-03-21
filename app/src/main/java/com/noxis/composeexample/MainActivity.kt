package com.noxis.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pointsData = getPointsList()
            val maxY = pointsData.maxOf { it.y }
            val minY = pointsData.minOf { it.y }
            val xAxisData = AxisData.Builder()
                .axisStepSize(100.dp)
//                .backgroundColor(Color.Blue)
                .steps(pointsData.size - 1)
                .labelData { i -> i.toString() }
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(stepsY)
//                .backgroundColor(Color.Red)
                .labelAndAxisLinePadding(20.dp)
                .labelData { i ->
                    val yScale = (maxY - minY) / stepsY.toFloat()
                    String.format("%.1f", ((i * yScale) + minY))
                }.build()

            val lineChartData = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = pointsData,
                            LineStyle(color = Color.Cyan),
                            IntersectionPoint(),
                            SelectionHighlightPoint(),
                            ShadowUnderLine(),
                            SelectionHighlightPopUp()
                        )
                    ),
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                gridLines = GridLines(),
                backgroundColor = Color.White
            )

            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                lineChartData = lineChartData
            )

        }
    }

    companion object {
        const val stepsY: Int = 10
    }
}

private fun getPointsList(): List<Point> {
    val listPoint = arrayListOf<Point>()
    (0..31).forEach {
        listPoint.add(
            Point(it.toFloat(), Random.nextInt(50, 90).toFloat())
        )
    }
    return listPoint
}
