package org.docear.plugin.pdfutilities.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.docear.plugin.pdfutilities.features.PDFReaderHandle;
import org.freeplane.core.util.TextUtils;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PDFXCVInvalidSettingsDialog extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox readerChoice;
	private final PDFReaderHandle[] readerHandles;

	public PDFXCVInvalidSettingsDialog(PDFReaderHandle[] handles) {
		readerHandles = handles;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;min)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JPanel pnlDialogInfo = new JPanel();
		pnlDialogInfo.setBackground(Color.WHITE);
		add(pnlDialogInfo, "1, 1, 2, 2, fill, fill");
		pnlDialogInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblHeadline = new JLabel(TextUtils.getText("docear.validate_pdf_xchange.headline"));
		lblHeadline.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnlDialogInfo.add(lblHeadline, "2, 2");
		
		JLabel lblNewLabel = new JLabel(TextUtils.getText("docear.validate_pdf_xchange.info"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		pnlDialogInfo.add(lblNewLabel, "2, 4");
		
		JPanel pnlForm = new JPanel();
		add(pnlForm, "2, 4, fill, fill");
		pnlForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblPdfReaderList = new JLabel(TextUtils.getText("docear.validate_pdf_xchange.choose_label"));
		pnlForm.add(lblPdfReaderList, "2, 2, right, default");
		
		lblPdfReaderList.setLabelFor(getReaderChoiceBox());
		pnlForm.add(getReaderChoiceBox(), "4, 2, fill, default");
		
		JLabel lblInfotext = new JLabel(TextUtils.getText("docear.validate_pdf_xchange.advice"));
		lblInfotext.setVerticalAlignment(SwingConstants.TOP);
		pnlForm.add(lblInfotext, "2, 4, 3, 1");
	}

	private JComboBox getReaderChoiceBox() {
		if(readerChoice == null) {
			readerChoice = new JComboBox();
			readerChoice.setModel(new DefaultComboBoxModel(readerHandles));
		} 
		return readerChoice;
	}
	
	

}