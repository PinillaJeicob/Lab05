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
        JComboBox<String> monthComboBox = new JComboBox<>(attendanceData.keySet().toArray(new String[0]));
        JButton filterButton = new JButton("Filtrar");

        filterPanel.add(new JLabel("Seleccionar mes:"));
        filterPanel.add(monthComboBox);
        filterPanel.add(filterButton);

       
        DefaultCategoryDataset dataset = createDataset(null);
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
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            DefaultCategoryDataset filteredDataset = createDataset(selectedMonth);
            barChart.getCategoryPlot().setDataset(filteredDataset);
        });

     
        add(filterPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset createDataset(String month) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (month == null) {
            
            for (Map.Entry<String, Integer> entry : attendanceData.entrySet()) {
                dataset.addValue(entry.getValue(), "Asistentes", entry.getKey());
            }
        } else {
            
            dataset.addValue(attendanceData.get(month), "Asistentes", month);
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

