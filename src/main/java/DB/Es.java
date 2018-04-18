package DB;
import java.net.InetAddress;
import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse.AnalyzeToken;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.search.MultiMatchQuery.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder; 

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Es {
	   private static String host = "120.79.216.1";
	    private static int port = 9200;
	    private TransportClient client = null;
	    public void getClient() throws Exception {
	        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
	        // 创建client
	        TransportClient client = new PreBuiltTransportClient(settings)
	                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9300));
	    
	    }
	  
	    public static void matchQuery(Client client ) {
            SearchResponse res = null;
            MatchQueryBuilder qb = QueryBuilders.matchQuery("title", "content");
            
            res = client.prepareSearch("search_test")
                            .setTypes("")
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                            .setQuery((org.elasticsearch.index.query.QueryBuilder) qb)
                            .setFrom(0)
                            .setSize(10)
                            .execute().actionGet();
            for (SearchHit hit: res.getHits().getHits()){
                    System.out.println(hit.getSourceAsString());
            }
	    }
	    
	    public static void termsQuery(Client client, String index, String type) {
	    	// Query
	    	TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("content", "比利时", "德国");
	    	// Search
	    	SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
	    	.setTypes(type)
	    	.setQuery(termsQueryBuilder);
	    	// 执行
	    	SearchResponse rs= searchRequestBuilder.get();
	    	// 结果
	    	
	    	}
	  /*  public static void prase() {
	    	JSONObject obj = new JSONObject();
	    	String pageName = obj.getJSONObject("pageInfo").getString("pageName");

	    	JSONArray arr = obj.getJSONArray("posts");
	    	for (int i = 0; i < arr.length(); i++)
	    	{
	    	    String post_id = arr.getJSONObject(i).getString("post_id");
	    	}
	    }
	    
	    */
	   

	    /**
	     * 关闭连接
	     */
	    public void close() {
	        if (client != null) {
	            client.close();
	        }
	    }
	    /*public static String matchMulti(Object text, String field, String fields) {
	        if (fields == null || fields.length() == 0) {
	            return match(field, text);
	        }

	        String[] newFields = new String[fields.length() + 1];
	        newFields[0] = field;
	        System.arraycopy(fields, 0, newFields, 1, fields.length());

	        return new MultiMatchQueryBuilder(text, newFields).toString();
	    }
	    */
	    public static String match(String field, Object text) {
	        return new MatchQueryBuilder(field, text).operator(Operator.AND).toString();
	    }
	    public static void token() {
	    	String content ="我是中国人";
	    	AnalyzeResponse response =ESTools.client.admin().indices().prepareAnalyze(content)
	    			.setAnalyzer("ik")
	    			.execute()
	    			.actionGet();
	    	List<AnalyzeToken>tokens = response.getTokens();
	    	String result = JSONArray.fromObject(tokens).toString();
	    	System.out.println(result);
	    }
	    public static void main(String[] args) {
	    	Es c = new Es(); 
	    	try { 
			        c.getClient();  
			        /*MultiMatchQueryBuilder qb = QueryBuilders  
		                    .multiMatchQuery("美国", "title", "content");  
		            SearchResponse sResponse = client.prepareSearch(index)  //指定索引库
		                    .setTypes(type)//指定类型
		                    .setQuery(qb)//指定查询条件
		                    .setFrom(0)
		                    .setSize(12)  
		                    .execute().actionGet();  
		  
		            SearchHits hits = sResponse.getHits();  
			        //SearchResponse searchResponse = c.client.prepareSearch("news")//指定索引库
			        	        .setTypes("sougou")//指定类型
			      
			                    .setQuery(QueryBuilders.multiMatchQuery("美国", "title","content"))//支持一个值同时匹配多个字段
			        	                .setExplain(true)//按照查询数据的匹配度返回数据
			        	               .get();
			        	                
		            /*if (null != hits && hits.totalHits() > 0) {  
		                for (SearchHit hit : hits) {  
		                    String json = hit.getSourceAsString();  
		                    Person newPerson = mapper.readValue(json, Person.class);  
		                    System.out.println("name\t\t" + newPerson.getName());  
		                    System.out.println("sex\t\t" + newPerson.getSex());  
		                    System.out.println("age\t\t" + newPerson.getAge());  
		                    System.out.println("isStudent\t\t"  
		                            + newPerson.getIsStudent());  
		                }  
		            } else {  
		                log.info("没有查询到任何结果！");  
		            }  
			        SearchHits hits = searchResponse.getHits();
			                long totalHits = hits.getTotalHits();
			                 System.out.println("总数："+totalHits);
			                
			               //获取满足条件数据的详细内容
			              SearchHit[] hits2 = hits.getHits();
			                System.out.println("总数2："+hits2.length);//totalHits 和hits2.length 获取的长度有什么区别
			                for (SearchHit searchHit : hits2) {
			                       System.out.println(searchHit.getSourceAsString());
			              }
				System.out.println("yes");
				}    */
			        List<DiscoveryNode> connectedNodes = c.client.connectedNodes();
			            for (DiscoveryNode discoveryNode : connectedNodes) {//for星型循环，将connectedNodes的值，一一传给DiscoveryNode discoveryNode
			                   System.out.println(discoveryNode.getHostName());//打印192.168.80.10;192.168.80.11;192.168.80.12
			                 //如果加入transportClient.addTransportAddresses(transportAddress)  只有一个ip,打印的就只有一个.
			                }
	    	}
				   catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}