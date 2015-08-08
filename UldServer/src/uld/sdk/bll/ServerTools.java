package uld.sdk.bll;

import java.util.List;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import wh.content.bll.Category;

public class ServerTools {
//	public static int getServerIdBySequenceNumber(int gameId, int sequenceNumber) {
//		uld.sdk.tools.LogHelper.log("gameId:"+gameId);
//		uld.sdk.tools.LogHelper.log("sequenceNumber:"+sequenceNumber);
//		
//		wh.game.model.Server queryModel = new wh.game.model.Server();
//		queryModel.setGameId(gameId);
//		queryModel.setSequenceNumber(sequenceNumber);
//		RefObject<Integer> totalCount = new RefObject<Integer>(0);
//		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
//		List<wh.game.model.Server> serverList = wh.game.bll.Server.getInstance().getList(queryModel, totalCount, true, 1, 1, "", 0, refMyErr);
//		if (serverList != null && serverList.size()>0) {
//			uld.sdk.tools.LogHelper.log("serverList.get(0).getServerId():"+serverList.get(0).getServerId());
//			return serverList.get(0).getServerId();
//		}
//		else {
//			uld.sdk.tools.LogHelper.log("serverList is null,so return:0");
//			return 0;			
//		}
//	}
	public static int getServerIdBySequenceNumber(int gameId, int sequenceNumber, String tag) {
		uld.sdk.tools.LogHelper.log(tag+"-gameId:"+gameId);
		uld.sdk.tools.LogHelper.log(tag+"-sequenceNumber:"+sequenceNumber);
		
		wh.game.model.Server queryModel = new wh.game.model.Server();
		queryModel.setGameId(gameId);
		queryModel.setSequenceNumber(sequenceNumber);
		RefObject<Integer> totalCount = new RefObject<Integer>(0);
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
		List<wh.game.model.Server> serverList = wh.game.bll.Server.getInstance().getList(queryModel, totalCount, true, 1, 1, "", 0, refMyErr);
		if (serverList != null && serverList.size()>0) {
			uld.sdk.tools.LogHelper.log(tag+"-serverList.get(0).getServerId():"+serverList.get(0).getServerId());
			return serverList.get(0).getServerId();
		}
		else {
			
			uld.sdk.tools.LogHelper.log(tag+"-serverList is null,so return:0");
			return 0;			
		}
	}
}
