import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;

public class JcoSample {
	public static void main(String[] args) throws JCoException {
		// destinationの取得
	        JCoDestination destination = JCoDestinationManager.getDestination("ABAP_AS_WITH_POOL");

		JcoRfcCall jcoRfcCall = new JcoRfcCall(destination, "RFC_READ_TABLE");
		
		// インポートパラメータを設定
		jcoRfcCall.setImportParameter(new HashMap<String, String>() {{
			put("QUERY_TABLE", "T001W"); // テーブル名
			put("DELIMITER", "\t"); // データのデリミタ
			put("NO_DATA", " "); // データがない場合の扱い
			put("ROWSKIPS", "0"); // スキップする行数
			put("ROWCOUNT", "10"); // 取得する行数
		}});
		// テーブルパラメータを設定
		List<Map<String, String>> tableParam = new ArrayList<Map<String, String>>();
		tableParam.add(new HashMap<String, String>() {{ put("FIELDNAME", "MANDT"); }});
		tableParam.add(new HashMap<String, String>() {{ put("FIELDNAME", "WERKS"); }});
		tableParam.add(new HashMap<String, String>() {{ put("FIELDNAME", "NAME1"); }});
		jcoRfcCall.setTableParameter("FIELDS", tableParam);
		
		// 実行
		jcoRfcCall.execute();
		
		// 結果処理
		List<Map<String, String>> headers = jcoRfcCall.getTableParameter("FIELDS"); // Select対象の列
		List<Map<String, String>> records = jcoRfcCall.getTableParameter("DATA"); // データ

		// ヘッダーを出力
		for(Map<String, String> header: headers) {
			System.out.print(header.get("FIELDNAME") + "\t");
		}
		System.out.print("\n");
		// データを出力
		for(Map<String, String> record: records) {
			System.out.println(record.get("WA")); // RFC_READ_TABLEはWAにデータがDELIMITERで区切られて格納される
		}
		 
		return;
	}
}