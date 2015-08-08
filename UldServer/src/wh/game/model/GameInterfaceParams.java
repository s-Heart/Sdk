package wh.game.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GameInterfaceParams implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GameInterfaceParams(){
		;
	}
		
	private int gameInterfaceParamsId;
	/** 
	 * 获取游戏接口参数编号
	 * @return 游戏接口参数编号
	 */
	public int getGameInterfaceParamsId()
	{
		return gameInterfaceParamsId;
	}
	
	/**
	 * 设置游戏接口参数编号
	 * @param 游戏接口参数编号
	 */
	public void setGameInterfaceParamsId(int value)
	{
		gameInterfaceParamsId = value;
	}
	

	private wh.game.model.GameInterface gameInterface;
	/**
	 * 获取游戏接口
	 * @return 游戏接口
	 */
	public wh.game.model.GameInterface getGameInterface(){
		return this.gameInterface;
	}
	
	/**
	 * 设置游戏接口
	 * @param gameInterface 游戏接口
	 */
	public void setGameInterface(wh.game.model.GameInterface gameInterface){
		this.gameInterface = gameInterface;
	}
	
	private int gameInterfaceId;
	/**
	 * 获取游戏接口编号
	 * @return 游戏接口编号
	 */
	public int getGameInterfaceId(){
		if(gameInterface != null){
			return gameInterface.getGameInterfaceId();
		}else{
			return gameInterfaceId;
		}
	}
	
	/**
	 * 设置游戏接口编号
	 * @param 游戏接口编号
	 */
	public void setGameInterfaceId(int value){
		if(gameInterface != null){
			gameInterface.setGameInterfaceId(value);
		}else{
			gameInterfaceId = value;
		}
	}

	/**
	 * 获取游戏接口名称
	 * @return 游戏接口名称
	 */
	public String getGameInterfaceName(){
		if(gameInterface != null){
			return gameInterface.getName();
		}else{
			return "";
		}
	
	}	

	private wh.game.model.Game game;
	/**
	 * 获取游戏
	 * @return 游戏
	 */
	public wh.game.model.Game getGame(){
		return this.game;
	}
	
	/**
	 * 设置游戏
	 * @param game 游戏
	 */
	public void setGame(wh.game.model.Game game){
		this.game = game;
	}
	
	private int gameId;
	/**
	 * 获取游戏编号
	 * @return 游戏编号
	 */
	public int getGameId(){
		if(game != null){
			return game.getGameId();
		}else{
			return gameId;
		}
	}
	
	/**
	 * 设置游戏编号
	 * @param 游戏编号
	 */
	public void setGameId(int value){
		if(game != null){
			game.setGameId(value);
		}else{
			gameId = value;
		}
	}
	/**
	 * 获取游戏名称
	 * @return 游戏名称
	 */
	public String getGameName(){
		if(game != null){
			return game.getGameName();
		}else{
			return "";
		}
	}	
	
	private String paramName;
	/** 
	 * 获取参数名
	 * @return 参数名
	 */
	public String getParamName()
	{
		return paramName;
	}
	
	/**
	 * 设置参数名
	 * @param 参数名
	 */
	public void setParamName(String value)
	{
		paramName = value;
	}
	
	
	private String paramValue;
	/** 
	 * 获取参数值
	 * @return 参数值
	 */
	public String getParamValue()
	{
		return paramValue;
	}
	
	/**
	 * 设置参数值
	 * @param 参数值
	 */
	public void setParamValue(String value)
	{
		paramValue = value;
	}
	
	
	private String paramExplain;
	/** 
	 * 获取说明
	 * @return 说明
	 */
	public String getParamExplain()
	{
		return paramExplain;
	}
	
	/**
	 * 设置说明
	 * @param 说明
	 */
	public void setParamExplain(String value)
	{
		paramExplain = value;
	}
	

	private byte signType;
	/**
	 * 获取签名类型：0、签名类型；1、参与签名；2、不参与签名；4、参与Base64签名；
	 * @return 签名类型：0、签名类型；1、参与签名；2、不参与签名；4、参与Base64签名；
	 */
	public byte getSignType(){
		return signType;
	}
	
	/**
	 * 设置签名类型：0、签名类型；1、参与签名；2、不参与签名；4、参与Base64签名；
	 * @param 签名类型：0、签名类型；1、参与签名；2、不参与签名；4、参与Base64签名；
	 */
	public void setSignType(byte value){
		signType = value;
	}	
	
	private byte signIndex;
	/** 
	 * 获取签名顺序，升充排列，从1开始
	 * @return 签名顺序，升充排列，从1开始
	 */
	public byte getSignIndex()
	{
		return signIndex;
	}
	
	/**
	 * 设置签名顺序，升充排列，从1开始
	 * @param 签名顺序，升充排列，从1开始
	 */
	public void setSignIndex(byte value)
	{
		signIndex = value;
	}
	

	private byte paramType;
	/**
	 * 获取参数类型：0、参数类型；1、传入参数；2、返回参数；
	 * @return 参数类型：0、参数类型；1、传入参数；2、返回参数；
	 */
	public byte getParamType(){
		return paramType;
	}
	
	/**
	 * 设置参数类型：0、参数类型；1、传入参数；2、返回参数；
	 * @param 参数类型：0、参数类型；1、传入参数；2、返回参数；
	 */
	public void setParamType(byte value){
		paramType = value;
	}	

	private byte paramInType;
	/**
	 * 获取传入参数类型：0、传入参数类型；1、帐号；2、时间；3、登录状态；4、游戏；5、服务器；6、联动商编号；7、防沉迷标识；8、签名；9、订单号；10、充值金额；11、签名Key；12、元宝；13、客户端IP；14、Base64签名；15、子渠道编号；
	 * @return 传入参数类型：0、传入参数类型；1、帐号；2、时间；3、登录状态；4、游戏；5、服务器；6、联动商编号；7、防沉迷标识；8、签名；9、订单号；10、充值金额；11、签名Key；12、元宝；13、客户端IP；14、Base64签名；15、子渠道编号；
	 */
	public byte getParamInType(){
		return paramInType;
	}
	
	/**
	 * 设置传入参数类型：0、传入参数类型；1、帐号；2、时间；3、登录状态；4、游戏；5、服务器；6、联动商编号；7、防沉迷标识；8、签名；9、订单号；10、充值金额；11、签名Key；12、元宝；13、客户端IP；14、Base64签名；15、子渠道编号；
	 * @param 传入参数类型：0、传入参数类型；1、帐号；2、时间；3、登录状态；4、游戏；5、服务器；6、联动商编号；7、防沉迷标识；8、签名；9、订单号；10、充值金额；11、签名Key；12、元宝；13、客户端IP；14、Base64签名；15、子渠道编号；
	 */
	public void setParamInType(byte value){
		paramInType = value;
	}	

	private byte paramOutType;
	/**
	 * 获取传出参数类型：0、传出参数类型；1、成功；2、失败；
	 * @return 传出参数类型：0、传出参数类型；1、成功；2、失败；
	 */
	public byte getParamOutType(){
		return paramOutType;
	}
	
	/**
	 * 设置传出参数类型：0、传出参数类型；1、成功；2、失败；
	 * @param 传出参数类型：0、传出参数类型；1、成功；2、失败；
	 */
	public void setParamOutType(byte value){
		paramOutType = value;
	}	
	
	private String signFormatValue;
	/** 
	 * 获取签名格式化值，eg:UserName={0}，如果此值为空，则取ParmValue
	 * @return 签名格式化值，eg:UserName={0}，如果此值为空，则取ParmValue
	 */
	public String getSignFormatValue()
	{
		return signFormatValue;
	}
	
	/**
	 * 设置签名格式化值，eg:UserName={0}，如果此值为空，则取ParmValue
	 * @param 签名格式化值，eg:UserName={0}，如果此值为空，则取ParmValue
	 */
	public void setSignFormatValue(String value)
	{
		signFormatValue = value;
	}
	
}	


