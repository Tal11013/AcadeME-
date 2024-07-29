package Pages.Teacher;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CourseDistributionGraph extends JPanel {
    private int[] distribution;
    private double average;
    private double standardDeviation;

    public CourseDistributionGraph(int[] distribution, double average, double standardDeviation) {
        this.distribution = distribution;
        this.average = average;
        this.standardDeviation = standardDeviation;
        this.setPreferredSize(new Dimension(400, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int graphWidth = getWidth() - 150;
        int graphHeight = getHeight() - 50;
        int barWidth = graphWidth / distribution.length;
        int maxValue = getMaxValue(distribution);

        // Draw x-axis and y-axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(30, getHeight() - 20, getWidth() - 120, getHeight() - 20); // x-axis
        g2d.drawLine(30, getHeight() - 20, 30, getHeight() - graphHeight); // y-axis

        // Draw x-axis labels (grade ranges)
        for (int i = 0; i < distribution.length; i++) {
            String gradeRange = getGradeRange(i);
            int labelX = 30 + i * barWidth + barWidth / 2;
            int labelY = getHeight() - 5;
            g2d.drawString(gradeRange, labelX, labelY);
        }

        // Draw bars and y-axis labels (number of students)
        for (int i = 0; i < distribution.length; i++) {
            int barX = 30 + i * barWidth + 5;
            int barHeight = (int) ((double) distribution[i] / maxValue * graphHeight);
            int barY = getHeight() - 20 - barHeight;
            g2d.setColor(Color.BLUE);
            g2d.fillRect(barX, barY, barWidth - 10, barHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(distribution[i]), barX + barWidth / 2, barY - 5);
        }

        // Draw average and standard deviation on the right side
        DecimalFormat df = new DecimalFormat("#.##");
        String averageLabel = "Average: " + df.format(average);
        String stdDeviationLabel = "Std Deviation: " + df.format(standardDeviation);
        g2d.drawString(averageLabel, getWidth() - 120, 50);
        g2d.drawString(stdDeviationLabel, getWidth() - 120, 70);
    }

    private int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private String getGradeRange(int index) {
        switch (index) {
            case 0:
                return "0-60";
            case 1:
                return "61-70";
            case 2:
                return "71-80";
            case 3:
                return "81-90";
            case 4:
                return "91-100";
            default:
                return "";
        }
    }
}
