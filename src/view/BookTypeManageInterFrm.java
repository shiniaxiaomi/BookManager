package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import dao.BookTypeDao;
import model.BookType;
import util.DbUtil;
import util.StringUtil;

public class BookTypeManageInterFrm extends JInternalFrame {
	private JTable bookTypeTable;
	
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u522B\u7C7B\u7BA1\u7406");
		setBounds(100, 100, 529, 594);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0:");
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);//图书类别查询事件
			}
		});
		button.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/search.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(39, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label)
								.addGap(18)
								.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addGap(35)
								.addComponent(button))))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(42)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_1 = new JLabel("\u7F16\u53F7:");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0:");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u63CF\u8FF0:");
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);//图书类别更新事件
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/modify.png")));
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);//图书类别删除事件
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addGap(14)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(bookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
									.addComponent(btnNewButton_1)
									.addGap(58)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);//表格行鼠标点击事件
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u63CF\u8FF0\u56FE\u4E66\u7C7B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(93);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookType());//初始化表格
	}
	

	/**
	 * 图书类别删除事件
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent e) {
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录!");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n==0) {
			Connection con=null;
			try {
				con=dbUtil.getCon();//获取数据库连接
				boolean flag=bookDao.existBookByBookTypeId(con, id);
				if(flag) {
					JOptionPane.showMessageDialog(null, "当前类别下有图书，不能删除此类别!");
					return;
				}
				int deleteNum=bookTypeDao.delete(con, id);
				if(deleteNum==1) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetValue();//重置表单
					this.fillTable(new BookType());
				}else {
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败!");
			}finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 图书类别修改事件
	 * @param e
	 */
	private void bookTypeUpdateActionEvent(ActionEvent e) {
		String id=idTxt.getText();
		String bookTypeName=bookTypeNameTxt.getText();
		String bookTypeDesc=bookTypeDescTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能空!");
			return;
		}
		BookType bookType=new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();//获取数据库连接
			int modifyNum=bookTypeDao.update(con, bookType);//更新数据,并返回更新的条数
			if(modifyNum==1) {
				JOptionPane.showMessageDialog(null, "修改成功!");
				resetValue();//修改成功之后重置表单
				this.fillTable(new BookType());//把更新后的数据更新到表单
			}else {
				JOptionPane.showMessageDialog(null, "修改失败!");
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败!");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 表格行鼠标点击事件
	 * @param e
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		int row=bookTypeTable.getSelectedRow();//获取鼠标点击的表格行号
		idTxt.setText((String)bookTypeTable.getValueAt(row, 0));//显示鼠标点击的那一行的第0列的信息
		bookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));//显示鼠标点击的那一行的第1列的信息
		bookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));//显示鼠标点击的那一行的第2列的信息
	}

	/**
	 * 图书类别查询按钮处理事件
	 * @param evt
	 */
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_bookTypeName=this.s_bookTypeNameTxt.getText();//获取当前用户输入的要所有的图书名称
		BookType bookType=new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		this.fillTable(bookType);
	}

	/**
	 * 初始化表格,并且带有查询的功能，一开始初始化是吧数据库中所有的图书别类都显示出来了
	 * @param bookType
	 */
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm=(DefaultTableModel) bookTypeTable.getModel();
		dtm.setRowCount(0);//吧表格设置成0行，即清空表格
		
		Connection con=null;
		try {
			con=dbUtil.getCon();//获取数据库连接
			ResultSet rs=bookTypeDao.list(con, bookType);//从数据库查询之后返回结果集
			while(rs.next()) {//遍历结果集
				Vector v=new Vector();//可变数组Vector
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
				dtm.addRow(v);//吧数据添加到表格中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.idTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
	
}
