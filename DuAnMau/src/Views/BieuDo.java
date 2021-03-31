package Views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import jdk.jfr.Category;

public class BieuDo {
	@SuppressWarnings("deprecation")
	public void thongKeDiem(JTable table) {
		double yeu, gioi, kha, trungBinh, kem;
		gioi = 0;
		kha = 0;
		trungBinh = 0;
		yeu =0;
		kem = 0;
		int tong =table.getRowCount();

		for (int i = 0; i < table.getRowCount(); i++) {
			if (String.valueOf(table.getValueAt(i, 4)).equalsIgnoreCase("Giỏi")) {
				gioi += 1;
			} else if (String.valueOf(table.getValueAt(i, 4)).equalsIgnoreCase("Khá")) {
				kha += 1;
			} else if (String.valueOf(table.getValueAt(i, 4)).equalsIgnoreCase("Trung Bình")) {
				trungBinh += 1;
			} else if (String.valueOf(table.getValueAt(i, 4)).equalsIgnoreCase("Yếu")) {
				yeu += 1;
			} else if (String.valueOf(table.getValueAt(i, 4)).equalsIgnoreCase("Kém")) {
				kem += 1;
			} 
		}
		DefaultPieDataset dataPieChart = new DefaultPieDataset();
		dataPieChart.setValue("Giỏi", new Double((gioi / tong )* 100));
		dataPieChart.setValue("Khá", new Double((kha / tong) * 100));
		dataPieChart.setValue("Trung bình", new Double((trungBinh) / tong * 100));
		dataPieChart.setValue("Kém", new Double((yeu / tong) * 100));
		dataPieChart.setValue("Yếu", new Double((kem / tong) * 100));
		JFreeChart freeChart = ChartFactory.createPieChart("Biểu đồ thống kê xếp loại học lực trong khóa", dataPieChart,
				true, true, true);
		ChartPanel chartPanel1 = new ChartPanel(freeChart);
		JFrame bieuDo = new JFrame();
		bieuDo.getContentPane().add(chartPanel1);
		bieuDo.setTitle("Biểu đồ thống kê xếp loại học lực trong khóa");
		bieuDo.setSize(400, 300);
		bieuDo.setLocationRelativeTo(null);
		bieuDo.setResizable(false);
		bieuDo.setVisible(true);

	}
	public void thongKeNguoiHoc(JTable table) {
		int cout = table.getRowCount();
		DefaultCategoryDataset x = new DefaultCategoryDataset();
		for (int i = 0; i < cout; i++) {
			int nguoiHoc= Integer.parseInt(String.valueOf(table.getValueAt(i, 1)));
			String nam = String.valueOf(table.getValueAt(i, 0));
			x.setValue(nguoiHoc, "Số Lượng", nam);
		}
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Thống Kê Người Học", "Năm","Số Lượng", x,
				PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		ChartFrame chartfram = new ChartFrame("Biểu Đồ Thống Kê", chart, true);
		chartfram.setVisible(true);
		chartfram.setSize(700, 700);
		chartfram.setLocationRelativeTo(null);
	}
	public void thongKeDoanhThu(JTable table) {
		int cout = table.getRowCount();
		DefaultCategoryDataset x = new DefaultCategoryDataset();
		for (int i = 0; i < cout; i++) {
			double doanhthu=Double.parseDouble(String.valueOf(table.getValueAt(i, 3)));
			String chuyenDe = String.valueOf(table.getValueAt(i, 0));
			x.setValue(doanhthu, "Tiền", chuyenDe);
		}
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Thống Kê Doanh Thu", "Chuyên Đề","Tiền", x,
				PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		ChartFrame chartfram = new ChartFrame("Biểu Đồ Thống Kê", chart, true);
		chartfram.setVisible(true);
		chartfram.setSize(700, 700);
		chartfram.setLocationRelativeTo(null);
	}
	public void thongKeDiemChuyenDe(JTable table) {
		int cout = table.getRowCount();
		DefaultCategoryDataset x = new DefaultCategoryDataset();
		for (int i = 0; i < cout; i++) {
			double diem=Double.parseDouble(String.valueOf(table.getValueAt(i,4)));
			String chuyenDe = String.valueOf(table.getValueAt(i, 0));
			x.setValue(diem, "Điểm", chuyenDe);
		}
		JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Thống Kê Điểm Chuyên Đề", "Chuyên Đề","Điểm Trung Bình", x,
				PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);
		ChartFrame chartfram = new ChartFrame("Biểu Đồ Thống Kê", chart, true);
		chartfram.setVisible(true);
		chartfram.setSize(700, 700);
		chartfram.setLocationRelativeTo(null);
	}
}
