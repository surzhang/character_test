package com.yun.utils;




import com.yun.entity.LayUiTree;
import com.yun.entity.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 树状菜单工具类
 * 
 * @author teacherChen
 */
public class TreeUtils {
    /**
     * @create by: Teacher陈（86521760@qq.com）
     * @description:  通过menulist返回treeList
     * @create time: 2021/12/6 15:45
     * @param menuList
     * @param parentId
     * @return java.util.List<com.cloudwise.entity.LayUiTree>
     */
    public static List<LayUiTree> getTreeListfromMenuList(List<Menu> menuList,Integer parentId){
        List<LayUiTree> layUiTreeList= new ArrayList<>();
        for (Menu menu : menuList) {
            //判断传入的parentId是否有孩子
            if(menu.getParentId().equals(parentId)){
                LayUiTree tree = getLayUiTreeFromMenu(menu);
                //找孩子，递归调用
                tree = recursion(tree, menuList);
                layUiTreeList.add(tree);
            }
        }
        return layUiTreeList;
    }
    /**
     * @create by: Teacher陈（86521760@qq.com）
     * @description: 递归调用，匹配孩子
     * @create time: 2021/12/6 16:00
     * @param
     * @return com.cloudwise.entity.LayUiTree
     */
    public static LayUiTree recursion(LayUiTree tree,List<Menu> menuList){
        //定义一个集合，装孩子用的
        List<LayUiTree> childNodes=new ArrayList<>();
        for (Menu node : menuList) {
            if(node.getParentId().equals(tree.getId())){
                LayUiTree treeNode = getLayUiTreeFromMenu(node);
                //递归调用
                childNodes.add(recursion(treeNode,menuList));
            }
        }
        tree.setChildren(childNodes);

        return tree;
    }
    /**
     * @create by: Teacher陈（86521760@qq.com）
     * @description:传入menu返回layuitree
     * @create time: 2021/12/6 16:22
     * @param menu
     * @return com.cloudwise.entity.LayUiTree
     */
    public static  LayUiTree getLayUiTreeFromMenu( Menu menu){
        LayUiTree tree= new LayUiTree();
        tree.setId(menu.getMenuId());
        tree.setTitle(menu.getMenuName());
        tree.setUrl(menu.getUrl());
        tree.setIcon(menu.getIcon());
        return tree;
    }
    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<LayUiTree> getChildPerms(List<Menu> list, int parentId) {
        List<LayUiTree> returnList = new ArrayList<LayUiTree>();
        for (Menu menu : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if(menu.getParentId()==parentId){
                LayUiTree tree = fromMenuToTree(menu);
                //开始递归，把当前菜单和所有菜单放入递归，给当前的tree找孩子
                LayUiTree layUiTree = recursionFn(tree, list);
                returnList.add(layUiTree);
            }
        }
        return returnList;
    }

    /**
     * 递归调用，给每一个tree配上自己的孩子
     * @param layUiTree
     * @param list
     * @return
     */
    private static LayUiTree recursionFn(LayUiTree layUiTree,List<Menu> list)
    {
        List<LayUiTree> childNodes = new ArrayList<>();
        for (Menu node : list) {
            if (node.getParentId().equals(layUiTree.getId())) {
                LayUiTree tree = fromMenuToTree(node);
                childNodes.add(recursionFn(tree, list));
            }
        }
        layUiTree.setChildren(childNodes);
        return layUiTree;
    }

    /**
     * 将menu对象转换成tree对象
     * @param menu
     * @return
     */
    private static LayUiTree fromMenuToTree(Menu menu){
        //构造tree对象
        LayUiTree tree= new LayUiTree();
        tree.setId(menu.getMenuId());
        tree.setTitle(menu.getMenuName());
        tree.setChecked(false);
        tree.setUrl(menu.getUrl());
        tree.setIcon(menu.getIcon());
        return tree;
    }

}
