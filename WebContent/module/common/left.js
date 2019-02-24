var __Menu;
 var __Menuhref=[];
 
 var alldata=[];
 var __MenuIconCls=[];
 function deletealldata()
{
    delete __Menu;
    delete __Menuhref;
}
    Ext.onReady(function(){
    	var dItem=''; 
    	var menus = [];

    	var roleType = dmt.user.roleType;
    	if (roleType == '0') {
    		menus = [  
    		        {title:'后台管理',iconCls:'nav',type:'T',param:[
    		       		{id:'01',href:'./module/deviceStation.jsp',rights:'',ctype:'B',text : '注册点管理',iconCls:''},
    		       		{id:'02',href:'./module/deviceUser.jsp',rights:'',ctype:'B',text : '设备管理',iconCls:''},
    		       		{id:'03',href:'./module/deviceParamNew.jsp',rights:'',ctype:'B',text : '通道参数管理',iconCls:''},
    		       		{id:'04',href:'./module/channelUser.jsp',rights:'',ctype:'B',text : '通道权限管理',iconCls:''},
    		       		{id:'05',href:'./module/user.jsp',rights:'',ctype:'B',text : '用户管理',iconCls:''},
    		       		{id:'06',href:'./module/userGroup.jsp',rights:'',ctype:'B',text : '用户组管理',iconCls:''},
    		       		{id:'07',href:'./module/sysParam.jsp',rights:'',ctype:'B',text : '系统参数管理',iconCls:''}
    		         ] 
    		        }
    		 	];
    	} 
    	
         __Menu=menus;
        if(__Menu=="")
        {
        	 location=__UrlBase + "login.jsp";
             return;
        }
        
        var parentId=new Array();
        if(dItem.length>0)
        {
             parentId=dItem.split(',');
        }
       
        var nodeType=new Array("T","M","W","G","S");
        var wests=[];
        for(var k=0;k<nodeType.length;k++)
        {
                 for(var  i=0;i<menus.length;i++)
                {
                        if(menus[i].type==nodeType[k])
                        {
                            var parentFlag=false;
                            for(var p=0;p<parentId.length;p++)
                            {
                                if(menus[i].id==parentId[p])
                                {
                                    parentFlag=true;
                                }
                             }
                             
                             if(parentFlag==true)
                             {
                                continue;
                             }
                             
                                var tItem1;
                                if( nodeType[k]=="M")
                                {
                                    tItem1={
                                        text:menus[i].title,
                                         leaf :false
                                    };
                                }
                                else
                                {
                                    tItem1={
                                        text:menus[i].title
                                    }; 
                                }
                                
                               
                                var param=[];
                                for(var j=0;j<menus[i].param.length;j++)
                                {
                                      var childrenFlag=false;
                                        for(var z=0;z<parentId.length;z++)
                                        {
                                            if(menus[i].param[j].id==parentId[z])
                                            {
                                                childrenFlag=true;
                                            }
                                         }
                                         
                                         if(childrenFlag==true)
                                         {
                                            continue;
                                         }
                             
                                    var treeItem={
                                        leaf : true,
                                        id:menus[i].param[j].id,
                                        text:menus[i].param[j].text
                                    };
                                    var item={};
                                    if(typeof(menus[i].param[j].iconCls)!='undefined')
                                    {
                                        treeItem.iconCls=menus[i].param[j].iconCls;
                                    }
                                    if(typeof(menus[i].param[j].rights)!='undefined')
                                    {
                                        if(menus[i].param[j].rights!='0')
                                        {
                                            
                                             treeItem.hidden=false;
                                            item.id=menus[i].param[j].id;
                                            item.href=menus[i].param[j].href;
                                        }
                                        else
                                        {
                                             treeItem.hidden=true;
                                        }
                                    }else
                                    {
                                            
                                            treeItem.hidden=false;
                                           
                                            
                                            item.id=menus[i].param[j].id;
                                            item.href=menus[i].param[j].href;
                                    }
                                   __Menuhref.push(item);
                                    param.push(treeItem);
                                }
                                if(typeof(menus[i].iconCls)!='undefined')
                                {
                                       tItem1.iconCls=menus[i].iconCls;
                                }
                                if(nodeType[k]=="M")
                                {
                                   tItem1.children=param;
                                   alldata.push(tItem1);
                                    var  menuIC={
                                        text:'业务处理',
                                        iconCls:'nav'
                                     };
                                   __MenuIconCls.push(menuIC);
                                  
                                }else
                                {
                                    alldata=param;
                                    __MenuIconCls.push(tItem1);
                                }
                               
                           }
                     
                }
               var root = new Ext.tree.AsyncTreeNode({
			    text : "用户管理",
			    draggable : false,
			    children : alldata
			    });
       
                var treenode=new Ext.tree.TreePanel({
			        //如果超出范围带自动滚动条
			        autoScroll:true,
			        animate:true,
			        root:root,
			        //默认根目录不显示
			        rootVisible:false,
			        border:false,
			        animate:true,
			        lines:true,
			        enableDD:false,//是否允许拖动节点
			        containerScroll:true,
			         listeners:
                    {
                        "click":function(node,event)
                        {
				if(node.isLeaf())
		                {
					 var aa= document.getElementById("test");
                          
                          for(var i=0;i<__Menuhref.length;i++)
                          {
                                if(__Menuhref[i].id==node.id)
                                {
                                   
                                   document.getElementById("test").setAttribute("href",__Menuhref[i].href);
                                    
                                     document.getElementById("test").click();
                                }
                          }
				}else
				{
					event.stopEvent();
					node.toggle();
				}
                          
                            
                        }
                    }
		        });
    		    if(alldata.length>0)
    		    {
		            var west1=new Ext.Panel({
                            id:'west'+k,
                            title:__MenuIconCls[0].text,
                           autoScroll:true,
                            border:false,
                           iconCls:__MenuIconCls[0].iconCls,
                           items:[treenode]
                    });
					wests.push(west1);
                    
                }
		        alldata=[];
		      __MenuIconCls=[];
        }
       var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
              {
                region:'west',
                id:'west-panel',
                title:'功能导航',
                split:true,
                width: 230,
                minSize: 175,
                maxSize: 400,
                collapsible: false,
                layout:'accordion',
                layoutConfig:{
                    animate:true
                },
                items: wests
            },
            {
                region:'center',
                border:false,
                autoScroll:true,
				layout:'fit',
				hidden:true,
                items:[{
                    baseCls:'x-plain',
                    bodyStyle:'padding:0px',
					layout:'fit',
                    items:{
                     autoScroll:false,
					   border:false
                    }
                }]
            }
            ]
        });
    });