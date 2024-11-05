//programa de asistentes a capacitaciones de nuevas tecnologias 2024 con filtros mejorados (cambio de filtros del mes por filtros de cantidad de asistentes)
//trabajado con Pablo Ovalle
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.attendancechart;

/**
 *
 * @author Multipropósito2
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AttendanceChart extends JFrame {

    private Map<String, Integer> attendanceData;

    public AttendanceChart() {
        attendanceData = new HashMap<>();
        attendanceData.put("Febrero", 52);
        attendanceData.put("Marzo", 1);
        attendanceData.put("Abril", 66);
        attendanceData.put("Mayo", 210);
        attendanceData.put("Junio", 4);
        attendanceData.put("Julio", 311);
        attendanceData.put("Agosto", 26);
        attendanceData.put("Septiembre", 30);

        initUI();
    }

    private void initUI() {
        setTitle("Asistentes a Capacitaciones 2024");

        JPanel filterPanel = new JPanel();
        JComboBox<String> attendanceRangeComboBox = new JComboBox<>(new String[]{"Todos", "Alta (>100)", "Media (50-100)", "Baja (<50)"});
        JButton filterButton = new JButton("Filtrar");

        filterPanel.add(new JLabel("Seleccionar rango de asistencia:"));
        filterPanel.add(attendanceRangeComboBox);
        filterPanel.add(filterButton);

        DefaultCategoryDataset dataset = createDataset("Todos");
        JFreeChart barChart = ChartFactory.createBarChart(
                "Asistentes por Mes",
                "Mes",
                "Número de Asistentes",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        filterButton.addActionListener(e -> {
            String selectedRange = (String) attendanceRangeComboBox.getSelectedItem();
            DefaultCategoryDataset filteredDataset = createDataset(selectedRange);
            barChart.getCategoryPlot().setDataset(filteredDataset);
        });

        add(filterPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset createDataset(String range) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : attendanceData.entrySet()) {
            String entryMonth = entry.getKey();
            int attendees = entry.getValue();

            if (range.equals("Todos") ||
                    (range.equals("Alta (>100)") && attendees > 100) ||
                    (range.equals("Media (50-100)") && attendees >= 50 && attendees <= 100) ||
                    (range.equals("Baja (<50)") && attendees < 50)) {
                dataset.addValue(attendees, "Asistentes", entryMonth);
            }
        }

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendanceChart chart = new AttendanceChart();
            chart.setVisible(true);
        });
    }
}


