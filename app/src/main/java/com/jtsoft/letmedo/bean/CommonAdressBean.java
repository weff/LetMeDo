package com.jtsoft.letmedo. bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 * 地址管理的bean类
 */

public class CommonAdressBean implements Serializable{

    /**
     * code : 200
     * message : null
     * response : {"provinces":[{"provId":320000,"provName":"江苏","isUsed":1,"cities":[{"cityId":320100,"provId":320000,"cityName":"南京","isUsed":1,"districts":[{"districtId":320102,"cityId":320100,"districtName":"玄武","isUsed":1},{"districtId":320104,"cityId":320100,"districtName":"秦淮","isUsed":1},{"districtId":320105,"cityId":320100,"districtName":"建邺","isUsed":1},{"districtId":320106,"cityId":320100,"districtName":"鼓楼","isUsed":1},{"districtId":320111,"cityId":320100,"districtName":"浦口","isUsed":1},{"districtId":320113,"cityId":320100,"districtName":"栖霞","isUsed":1},{"districtId":320114,"cityId":320100,"districtName":"雨花台","isUsed":1},{"districtId":320115,"cityId":320100,"districtName":"江宁","isUsed":1},{"districtId":320116,"cityId":320100,"districtName":"六合","isUsed":1},{"districtId":320117,"cityId":320100,"districtName":"溧水","isUsed":1},{"districtId":320118,"cityId":320100,"districtName":"高淳","isUsed":1}]},{"cityId":320200,"provId":320000,"cityName":"无锡","isUsed":1,"districts":[{"districtId":320202,"cityId":320200,"districtName":"崇安","isUsed":1},{"districtId":320203,"cityId":320200,"districtName":"南长","isUsed":1},{"districtId":320204,"cityId":320200,"districtName":"北塘","isUsed":1},{"districtId":320205,"cityId":320200,"districtName":"锡山","isUsed":1},{"districtId":320206,"cityId":320200,"districtName":"惠山","isUsed":1},{"districtId":320211,"cityId":320200,"districtName":"滨湖","isUsed":1},{"districtId":320281,"cityId":320200,"districtName":"江阴","isUsed":1},{"districtId":320282,"cityId":320200,"districtName":"宜兴","isUsed":1}]},{"cityId":320300,"provId":320000,"cityName":"徐州","isUsed":1,"districts":[{"districtId":320302,"cityId":320300,"districtName":"鼓楼","isUsed":1},{"districtId":320303,"cityId":320300,"districtName":"云龙","isUsed":1},{"districtId":320305,"cityId":320300,"districtName":"贾汪","isUsed":1},{"districtId":320311,"cityId":320300,"districtName":"泉山","isUsed":1},{"districtId":320312,"cityId":320300,"districtName":"铜山","isUsed":1},{"districtId":320321,"cityId":320300,"districtName":"丰县","isUsed":1},{"districtId":320322,"cityId":320300,"districtName":"沛县","isUsed":1},{"districtId":320324,"cityId":320300,"districtName":"睢宁","isUsed":1},{"districtId":320381,"cityId":320300,"districtName":"新沂","isUsed":1},{"districtId":320382,"cityId":320300,"districtName":"邳州","isUsed":1}]},{"cityId":320400,"provId":320000,"cityName":"常州","isUsed":1,"districts":[{"districtId":320402,"cityId":320400,"districtName":"天宁","isUsed":1},{"districtId":320404,"cityId":320400,"districtName":"钟楼","isUsed":1},{"districtId":320405,"cityId":320400,"districtName":"戚墅堰","isUsed":1},{"districtId":320411,"cityId":320400,"districtName":"新北","isUsed":1},{"districtId":320412,"cityId":320400,"districtName":"武进","isUsed":1},{"districtId":320481,"cityId":320400,"districtName":"溧阳","isUsed":1},{"districtId":320482,"cityId":320400,"districtName":"金坛","isUsed":1}]},{"cityId":320500,"provId":320000,"cityName":"苏州","isUsed":1,"districts":[{"districtId":320505,"cityId":320500,"districtName":"虎丘","isUsed":1},{"districtId":320506,"cityId":320500,"districtName":"吴中","isUsed":1},{"districtId":320507,"cityId":320500,"districtName":"相城","isUsed":1},{"districtId":320508,"cityId":320500,"districtName":"姑苏","isUsed":1},{"districtId":320581,"cityId":320500,"districtName":"常熟","isUsed":1},{"districtId":320582,"cityId":320500,"districtName":"张家港","isUsed":1},{"districtId":320583,"cityId":320500,"districtName":"昆山","isUsed":1},{"districtId":320584,"cityId":320500,"districtName":"吴江","isUsed":1},{"districtId":320585,"cityId":320500,"districtName":"太仓","isUsed":1}]},{"cityId":320600,"provId":320000,"cityName":"南通","isUsed":1,"districts":[{"districtId":320602,"cityId":320600,"districtName":"崇川","isUsed":1},{"districtId":320611,"cityId":320600,"districtName":"港闸","isUsed":1},{"districtId":320612,"cityId":320600,"districtName":"通州","isUsed":1},{"districtId":320621,"cityId":320600,"districtName":"海安","isUsed":1},{"districtId":320623,"cityId":320600,"districtName":"如东","isUsed":1},{"districtId":320681,"cityId":320600,"districtName":"启东","isUsed":1},{"districtId":320682,"cityId":320600,"districtName":"如皋","isUsed":1},{"districtId":320684,"cityId":320600,"districtName":"海门","isUsed":1}]},{"cityId":320700,"provId":320000,"cityName":"连云港","isUsed":1,"districts":[{"districtId":320703,"cityId":320700,"districtName":"连云","isUsed":1},{"districtId":320705,"cityId":320700,"districtName":"新浦","isUsed":1},{"districtId":320706,"cityId":320700,"districtName":"海州","isUsed":1},{"districtId":320721,"cityId":320700,"districtName":"赣榆","isUsed":1},{"districtId":320722,"cityId":320700,"districtName":"东海","isUsed":1},{"districtId":320723,"cityId":320700,"districtName":"灌云","isUsed":1},{"districtId":320724,"cityId":320700,"districtName":"灌南","isUsed":1}]},{"cityId":320800,"provId":320000,"cityName":"淮安","isUsed":1,"districts":[{"districtId":320802,"cityId":320800,"districtName":"清河","isUsed":1},{"districtId":320803,"cityId":320800,"districtName":"维安","isUsed":1},{"districtId":320804,"cityId":320800,"districtName":"淮阴","isUsed":1},{"districtId":320811,"cityId":320800,"districtName":"清浦","isUsed":1},{"districtId":320826,"cityId":320800,"districtName":"涟水","isUsed":1},{"districtId":320829,"cityId":320800,"districtName":"洪泽","isUsed":1},{"districtId":320830,"cityId":320800,"districtName":"盱眙","isUsed":1},{"districtId":320831,"cityId":320800,"districtName":"金湖","isUsed":1}]},{"cityId":320900,"provId":320000,"cityName":"盐城","isUsed":1,"districts":[{"districtId":320902,"cityId":320900,"districtName":"亭湖","isUsed":1},{"districtId":320903,"cityId":320900,"districtName":"盐都","isUsed":1},{"districtId":320921,"cityId":320900,"districtName":"响水","isUsed":1},{"districtId":320922,"cityId":320900,"districtName":"滨海","isUsed":1},{"districtId":320923,"cityId":320900,"districtName":"阜宁","isUsed":1},{"districtId":320924,"cityId":320900,"districtName":"射阳","isUsed":1},{"districtId":320925,"cityId":320900,"districtName":"建湖","isUsed":1},{"districtId":320981,"cityId":320900,"districtName":"东台","isUsed":1},{"districtId":320982,"cityId":320900,"districtName":"大丰","isUsed":1}]},{"cityId":321000,"provId":320000,"cityName":"扬州","isUsed":1,"districts":[{"districtId":321002,"cityId":321000,"districtName":"广陵","isUsed":1},{"districtId":321003,"cityId":321000,"districtName":"邗江","isUsed":1},{"districtId":321012,"cityId":321000,"districtName":"江都","isUsed":1},{"districtId":321023,"cityId":321000,"districtName":"宝应","isUsed":1},{"districtId":321081,"cityId":321000,"districtName":"仪征","isUsed":1},{"districtId":321084,"cityId":321000,"districtName":"高邮","isUsed":1}]},{"cityId":321100,"provId":320000,"cityName":"镇江","isUsed":1,"districts":[{"districtId":321102,"cityId":321100,"districtName":"京口","isUsed":1},{"districtId":321111,"cityId":321100,"districtName":"润州","isUsed":1},{"districtId":321112,"cityId":321100,"districtName":"丹徒","isUsed":1},{"districtId":321181,"cityId":321100,"districtName":"丹阳","isUsed":1},{"districtId":321182,"cityId":321100,"districtName":"扬中","isUsed":1},{"districtId":321183,"cityId":321100,"districtName":"句容","isUsed":1}]},{"cityId":321200,"provId":320000,"cityName":"泰州","isUsed":1,"districts":[{"districtId":321202,"cityId":321200,"districtName":"海陵","isUsed":1},{"districtId":321203,"cityId":321200,"districtName":"高港","isUsed":1},{"districtId":321204,"cityId":321200,"districtName":"姜堰","isUsed":1},{"districtId":321281,"cityId":321200,"districtName":"兴化","isUsed":1},{"districtId":321282,"cityId":321200,"districtName":"靖江","isUsed":1},{"districtId":321283,"cityId":321200,"districtName":"泰兴","isUsed":1}]},{"cityId":321300,"provId":320000,"cityName":"宿迁","isUsed":1,"districts":[{"districtId":321302,"cityId":321300,"districtName":"宿城","isUsed":1},{"districtId":321311,"cityId":321300,"districtName":"宿豫","isUsed":1},{"districtId":321322,"cityId":321300,"districtName":"沭阳","isUsed":1},{"districtId":321323,"cityId":321300,"districtName":"泗阳","isUsed":1},{"districtId":321324,"cityId":321300,"districtName":"泗洪","isUsed":1}]}]},{"provId":340000,"provName":"安徽","isUsed":1,"cities":[{"cityId":340100,"provId":340000,"cityName":"合肥","isUsed":1,"districts":[{"districtId":340102,"cityId":340100,"districtName":"瑶海","isUsed":1},{"districtId":340103,"cityId":340100,"districtName":"庐阳","isUsed":1},{"districtId":340104,"cityId":340100,"districtName":"蜀山","isUsed":1},{"districtId":340111,"cityId":340100,"districtName":"包河","isUsed":1},{"districtId":340121,"cityId":340100,"districtName":"长丰","isUsed":1},{"districtId":340122,"cityId":340100,"districtName":"肥东","isUsed":1},{"districtId":340123,"cityId":340100,"districtName":"肥西","isUsed":1},{"districtId":340124,"cityId":340100,"districtName":"庐江","isUsed":1},{"districtId":340181,"cityId":340100,"districtName":"巢湖","isUsed":1}]},{"cityId":340200,"provId":340000,"cityName":"芜湖","isUsed":1,"districts":[{"districtId":340202,"cityId":340200,"districtName":"镜湖","isUsed":1},{"districtId":340203,"cityId":340200,"districtName":"弋江","isUsed":1},{"districtId":340207,"cityId":340200,"districtName":"鸠江","isUsed":1},{"districtId":340208,"cityId":340200,"districtName":"三山","isUsed":1},{"districtId":340221,"cityId":340200,"districtName":"芜湖","isUsed":1},{"districtId":340222,"cityId":340200,"districtName":"繁昌","isUsed":1},{"districtId":340223,"cityId":340200,"districtName":"南陵","isUsed":1},{"districtId":340225,"cityId":340200,"districtName":"无为","isUsed":1}]},{"cityId":340300,"provId":340000,"cityName":"蚌埠","isUsed":1,"districts":[{"districtId":340302,"cityId":340300,"districtName":"龙子湖","isUsed":1},{"districtId":340303,"cityId":340300,"districtName":"蚌山","isUsed":1},{"districtId":340304,"cityId":340300,"districtName":"禹会","isUsed":1},{"districtId":340311,"cityId":340300,"districtName":"淮上","isUsed":1},{"districtId":340321,"cityId":340300,"districtName":"怀远","isUsed":1},{"districtId":340322,"cityId":340300,"districtName":"五河","isUsed":1},{"districtId":340323,"cityId":340300,"districtName":"固镇","isUsed":1}]},{"cityId":340400,"provId":340000,"cityName":"淮南","isUsed":1,"districts":[{"districtId":340402,"cityId":340400,"districtName":"大通","isUsed":1},{"districtId":340403,"cityId":340400,"districtName":"田家庵","isUsed":1},{"districtId":340404,"cityId":340400,"districtName":"谢家集","isUsed":1},{"districtId":340405,"cityId":340400,"districtName":"八公山","isUsed":1},{"districtId":340406,"cityId":340400,"districtName":"潘集","isUsed":1},{"districtId":340421,"cityId":340400,"districtName":"凤台","isUsed":1}]},{"cityId":340500,"provId":340000,"cityName":"马鞍山","isUsed":1,"districts":[{"districtId":340503,"cityId":340500,"districtName":"花山","isUsed":1},{"districtId":340504,"cityId":340500,"districtName":"雨山","isUsed":1},{"districtId":340506,"cityId":340500,"districtName":"博望","isUsed":1},{"districtId":340521,"cityId":340500,"districtName":"当涂","isUsed":1},{"districtId":340522,"cityId":340500,"districtName":"含山","isUsed":1},{"districtId":340523,"cityId":340500,"districtName":"和县","isUsed":1}]},{"cityId":340600,"provId":340000,"cityName":"淮北","isUsed":1,"districts":[{"districtId":340602,"cityId":340600,"districtName":"杜集","isUsed":1},{"districtId":340603,"cityId":340600,"districtName":"相山","isUsed":1},{"districtId":340604,"cityId":340600,"districtName":"烈山","isUsed":1},{"districtId":340621,"cityId":340600,"districtName":"濉溪","isUsed":1}]},{"cityId":340700,"provId":340000,"cityName":"铜陵","isUsed":1,"districts":[{"districtId":340702,"cityId":340700,"districtName":"铜官山","isUsed":1},{"districtId":340703,"cityId":340700,"districtName":"狮子山","isUsed":1},{"districtId":340711,"cityId":340700,"districtName":"郊区","isUsed":1},{"districtId":340721,"cityId":340700,"districtName":"铜陵","isUsed":1}]},{"cityId":340800,"provId":340000,"cityName":"安庆","isUsed":1,"districts":[{"districtId":340802,"cityId":340800,"districtName":"迎江","isUsed":1},{"districtId":340803,"cityId":340800,"districtName":"大观","isUsed":1},{"districtId":340811,"cityId":340800,"districtName":"宜秀","isUsed":1},{"districtId":340822,"cityId":340800,"districtName":"怀宁","isUsed":1},{"districtId":340823,"cityId":340800,"districtName":"枞阳","isUsed":1},{"districtId":340824,"cityId":340800,"districtName":"潜山","isUsed":1},{"districtId":340825,"cityId":340800,"districtName":"太湖","isUsed":1},{"districtId":340826,"cityId":340800,"districtName":"宿松","isUsed":1},{"districtId":340827,"cityId":340800,"districtName":"望江","isUsed":1},{"districtId":340828,"cityId":340800,"districtName":"岳西","isUsed":1},{"districtId":340881,"cityId":340800,"districtName":"桐城","isUsed":1}]},{"cityId":341000,"provId":340000,"cityName":"黄山","isUsed":1,"districts":[{"districtId":341002,"cityId":341000,"districtName":"屯溪","isUsed":1},{"districtId":341003,"cityId":341000,"districtName":"黄山","isUsed":1},{"districtId":341004,"cityId":341000,"districtName":"徽州","isUsed":1},{"districtId":341021,"cityId":341000,"districtName":"歙县","isUsed":1},{"districtId":341022,"cityId":341000,"districtName":"休宁","isUsed":1},{"districtId":341023,"cityId":341000,"districtName":"黟县","isUsed":1},{"districtId":341024,"cityId":341000,"districtName":"祁门","isUsed":1}]},{"cityId":341100,"provId":340000,"cityName":"滁州","isUsed":1,"districts":[{"districtId":341102,"cityId":341100,"districtName":"琅琊","isUsed":1},{"districtId":341103,"cityId":341100,"districtName":"南谯","isUsed":1},{"districtId":341122,"cityId":341100,"districtName":"来安","isUsed":1},{"districtId":341124,"cityId":341100,"districtName":"全椒","isUsed":1},{"districtId":341125,"cityId":341100,"districtName":"定远","isUsed":1},{"districtId":341126,"cityId":341100,"districtName":"凤阳","isUsed":1},{"districtId":341181,"cityId":341100,"districtName":"天长","isUsed":1},{"districtId":341182,"cityId":341100,"districtName":"明光","isUsed":1}]},{"cityId":341200,"provId":340000,"cityName":"阜阳","isUsed":1,"districts":[{"districtId":341202,"cityId":341200,"districtName":"颍州","isUsed":1},{"districtId":341203,"cityId":341200,"districtName":"颍东","isUsed":1},{"districtId":341204,"cityId":341200,"districtName":"颍泉","isUsed":1},{"districtId":341221,"cityId":341200,"districtName":"临泉","isUsed":1},{"districtId":341222,"cityId":341200,"districtName":"太和","isUsed":1},{"districtId":341225,"cityId":341200,"districtName":"阜南","isUsed":1},{"districtId":341226,"cityId":341200,"districtName":"颍上","isUsed":1},{"districtId":341282,"cityId":341200,"districtName":"界首","isUsed":1}]},{"cityId":341300,"provId":340000,"cityName":"宿州","isUsed":1,"districts":[{"districtId":341302,"cityId":341300,"districtName":"埇桥","isUsed":1},{"districtId":341321,"cityId":341300,"districtName":"砀山","isUsed":1},{"districtId":341322,"cityId":341300,"districtName":"萧县","isUsed":1},{"districtId":341323,"cityId":341300,"districtName":"灵璧","isUsed":1},{"districtId":341324,"cityId":341300,"districtName":"泗县","isUsed":1}]},{"cityId":341500,"provId":340000,"cityName":"六安","isUsed":1,"districts":[{"districtId":341502,"cityId":341500,"districtName":"金安","isUsed":1},{"districtId":341503,"cityId":341500,"districtName":"裕安","isUsed":1},{"districtId":341521,"cityId":341500,"districtName":"寿县","isUsed":1},{"districtId":341522,"cityId":341500,"districtName":"霍邱","isUsed":1},{"districtId":341523,"cityId":341500,"districtName":"舒城","isUsed":1},{"districtId":341524,"cityId":341500,"districtName":"金寨","isUsed":1},{"districtId":341525,"cityId":341500,"districtName":"霍山","isUsed":1}]},{"cityId":341600,"provId":340000,"cityName":"亳州","isUsed":1,"districts":[{"districtId":341602,"cityId":341600,"districtName":"谯城","isUsed":1},{"districtId":341621,"cityId":341600,"districtName":"涡阳","isUsed":1},{"districtId":341622,"cityId":341600,"districtName":"蒙城","isUsed":1},{"districtId":341623,"cityId":341600,"districtName":"利辛","isUsed":1}]},{"cityId":341700,"provId":340000,"cityName":"池州","isUsed":1,"districts":[{"districtId":341702,"cityId":341700,"districtName":"贵池","isUsed":1},{"districtId":341721,"cityId":341700,"districtName":"东至","isUsed":1},{"districtId":341722,"cityId":341700,"districtName":"石台","isUsed":1},{"districtId":341723,"cityId":341700,"districtName":"青阳","isUsed":1}]},{"cityId":341800,"provId":340000,"cityName":"宣城","isUsed":1,"districts":[{"districtId":341802,"cityId":341800,"districtName":"宣州","isUsed":1},{"districtId":341821,"cityId":341800,"districtName":"郎溪","isUsed":1},{"districtId":341822,"cityId":341800,"districtName":"广德","isUsed":1},{"districtId":341823,"cityId":341800,"districtName":"泾县","isUsed":1},{"districtId":341824,"cityId":341800,"districtName":"绩溪","isUsed":1},{"districtId":341825,"cityId":341800,"districtName":"旌德","isUsed":1},{"districtId":341881,"cityId":341800,"districtName":"宁国","isUsed":1}]}]}]}
     */

    private int code;
    private Object message;
    private ResponseBean response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private List<ProvincesBean> provinces;

        public List<ProvincesBean> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<ProvincesBean> provinces) {
            this.provinces = provinces;
        }

        public static class ProvincesBean implements Serializable{
            /**
             * provId : 320000
             * provName : 江苏
             * isUsed : 1
             * cities : [{"cityId":320100,"provId":320000,"cityName":"南京","isUsed":1,"districts":[{"districtId":320102,"cityId":320100,"districtName":"玄武","isUsed":1},{"districtId":320104,"cityId":320100,"districtName":"秦淮","isUsed":1},{"districtId":320105,"cityId":320100,"districtName":"建邺","isUsed":1},{"districtId":320106,"cityId":320100,"districtName":"鼓楼","isUsed":1},{"districtId":320111,"cityId":320100,"districtName":"浦口","isUsed":1},{"districtId":320113,"cityId":320100,"districtName":"栖霞","isUsed":1},{"districtId":320114,"cityId":320100,"districtName":"雨花台","isUsed":1},{"districtId":320115,"cityId":320100,"districtName":"江宁","isUsed":1},{"districtId":320116,"cityId":320100,"districtName":"六合","isUsed":1},{"districtId":320117,"cityId":320100,"districtName":"溧水","isUsed":1},{"districtId":320118,"cityId":320100,"districtName":"高淳","isUsed":1}]},{"cityId":320200,"provId":320000,"cityName":"无锡","isUsed":1,"districts":[{"districtId":320202,"cityId":320200,"districtName":"崇安","isUsed":1},{"districtId":320203,"cityId":320200,"districtName":"南长","isUsed":1},{"districtId":320204,"cityId":320200,"districtName":"北塘","isUsed":1},{"districtId":320205,"cityId":320200,"districtName":"锡山","isUsed":1},{"districtId":320206,"cityId":320200,"districtName":"惠山","isUsed":1},{"districtId":320211,"cityId":320200,"districtName":"滨湖","isUsed":1},{"districtId":320281,"cityId":320200,"districtName":"江阴","isUsed":1},{"districtId":320282,"cityId":320200,"districtName":"宜兴","isUsed":1}]},{"cityId":320300,"provId":320000,"cityName":"徐州","isUsed":1,"districts":[{"districtId":320302,"cityId":320300,"districtName":"鼓楼","isUsed":1},{"districtId":320303,"cityId":320300,"districtName":"云龙","isUsed":1},{"districtId":320305,"cityId":320300,"districtName":"贾汪","isUsed":1},{"districtId":320311,"cityId":320300,"districtName":"泉山","isUsed":1},{"districtId":320312,"cityId":320300,"districtName":"铜山","isUsed":1},{"districtId":320321,"cityId":320300,"districtName":"丰县","isUsed":1},{"districtId":320322,"cityId":320300,"districtName":"沛县","isUsed":1},{"districtId":320324,"cityId":320300,"districtName":"睢宁","isUsed":1},{"districtId":320381,"cityId":320300,"districtName":"新沂","isUsed":1},{"districtId":320382,"cityId":320300,"districtName":"邳州","isUsed":1}]},{"cityId":320400,"provId":320000,"cityName":"常州","isUsed":1,"districts":[{"districtId":320402,"cityId":320400,"districtName":"天宁","isUsed":1},{"districtId":320404,"cityId":320400,"districtName":"钟楼","isUsed":1},{"districtId":320405,"cityId":320400,"districtName":"戚墅堰","isUsed":1},{"districtId":320411,"cityId":320400,"districtName":"新北","isUsed":1},{"districtId":320412,"cityId":320400,"districtName":"武进","isUsed":1},{"districtId":320481,"cityId":320400,"districtName":"溧阳","isUsed":1},{"districtId":320482,"cityId":320400,"districtName":"金坛","isUsed":1}]},{"cityId":320500,"provId":320000,"cityName":"苏州","isUsed":1,"districts":[{"districtId":320505,"cityId":320500,"districtName":"虎丘","isUsed":1},{"districtId":320506,"cityId":320500,"districtName":"吴中","isUsed":1},{"districtId":320507,"cityId":320500,"districtName":"相城","isUsed":1},{"districtId":320508,"cityId":320500,"districtName":"姑苏","isUsed":1},{"districtId":320581,"cityId":320500,"districtName":"常熟","isUsed":1},{"districtId":320582,"cityId":320500,"districtName":"张家港","isUsed":1},{"districtId":320583,"cityId":320500,"districtName":"昆山","isUsed":1},{"districtId":320584,"cityId":320500,"districtName":"吴江","isUsed":1},{"districtId":320585,"cityId":320500,"districtName":"太仓","isUsed":1}]},{"cityId":320600,"provId":320000,"cityName":"南通","isUsed":1,"districts":[{"districtId":320602,"cityId":320600,"districtName":"崇川","isUsed":1},{"districtId":320611,"cityId":320600,"districtName":"港闸","isUsed":1},{"districtId":320612,"cityId":320600,"districtName":"通州","isUsed":1},{"districtId":320621,"cityId":320600,"districtName":"海安","isUsed":1},{"districtId":320623,"cityId":320600,"districtName":"如东","isUsed":1},{"districtId":320681,"cityId":320600,"districtName":"启东","isUsed":1},{"districtId":320682,"cityId":320600,"districtName":"如皋","isUsed":1},{"districtId":320684,"cityId":320600,"districtName":"海门","isUsed":1}]},{"cityId":320700,"provId":320000,"cityName":"连云港","isUsed":1,"districts":[{"districtId":320703,"cityId":320700,"districtName":"连云","isUsed":1},{"districtId":320705,"cityId":320700,"districtName":"新浦","isUsed":1},{"districtId":320706,"cityId":320700,"districtName":"海州","isUsed":1},{"districtId":320721,"cityId":320700,"districtName":"赣榆","isUsed":1},{"districtId":320722,"cityId":320700,"districtName":"东海","isUsed":1},{"districtId":320723,"cityId":320700,"districtName":"灌云","isUsed":1},{"districtId":320724,"cityId":320700,"districtName":"灌南","isUsed":1}]},{"cityId":320800,"provId":320000,"cityName":"淮安","isUsed":1,"districts":[{"districtId":320802,"cityId":320800,"districtName":"清河","isUsed":1},{"districtId":320803,"cityId":320800,"districtName":"维安","isUsed":1},{"districtId":320804,"cityId":320800,"districtName":"淮阴","isUsed":1},{"districtId":320811,"cityId":320800,"districtName":"清浦","isUsed":1},{"districtId":320826,"cityId":320800,"districtName":"涟水","isUsed":1},{"districtId":320829,"cityId":320800,"districtName":"洪泽","isUsed":1},{"districtId":320830,"cityId":320800,"districtName":"盱眙","isUsed":1},{"districtId":320831,"cityId":320800,"districtName":"金湖","isUsed":1}]},{"cityId":320900,"provId":320000,"cityName":"盐城","isUsed":1,"districts":[{"districtId":320902,"cityId":320900,"districtName":"亭湖","isUsed":1},{"districtId":320903,"cityId":320900,"districtName":"盐都","isUsed":1},{"districtId":320921,"cityId":320900,"districtName":"响水","isUsed":1},{"districtId":320922,"cityId":320900,"districtName":"滨海","isUsed":1},{"districtId":320923,"cityId":320900,"districtName":"阜宁","isUsed":1},{"districtId":320924,"cityId":320900,"districtName":"射阳","isUsed":1},{"districtId":320925,"cityId":320900,"districtName":"建湖","isUsed":1},{"districtId":320981,"cityId":320900,"districtName":"东台","isUsed":1},{"districtId":320982,"cityId":320900,"districtName":"大丰","isUsed":1}]},{"cityId":321000,"provId":320000,"cityName":"扬州","isUsed":1,"districts":[{"districtId":321002,"cityId":321000,"districtName":"广陵","isUsed":1},{"districtId":321003,"cityId":321000,"districtName":"邗江","isUsed":1},{"districtId":321012,"cityId":321000,"districtName":"江都","isUsed":1},{"districtId":321023,"cityId":321000,"districtName":"宝应","isUsed":1},{"districtId":321081,"cityId":321000,"districtName":"仪征","isUsed":1},{"districtId":321084,"cityId":321000,"districtName":"高邮","isUsed":1}]},{"cityId":321100,"provId":320000,"cityName":"镇江","isUsed":1,"districts":[{"districtId":321102,"cityId":321100,"districtName":"京口","isUsed":1},{"districtId":321111,"cityId":321100,"districtName":"润州","isUsed":1},{"districtId":321112,"cityId":321100,"districtName":"丹徒","isUsed":1},{"districtId":321181,"cityId":321100,"districtName":"丹阳","isUsed":1},{"districtId":321182,"cityId":321100,"districtName":"扬中","isUsed":1},{"districtId":321183,"cityId":321100,"districtName":"句容","isUsed":1}]},{"cityId":321200,"provId":320000,"cityName":"泰州","isUsed":1,"districts":[{"districtId":321202,"cityId":321200,"districtName":"海陵","isUsed":1},{"districtId":321203,"cityId":321200,"districtName":"高港","isUsed":1},{"districtId":321204,"cityId":321200,"districtName":"姜堰","isUsed":1},{"districtId":321281,"cityId":321200,"districtName":"兴化","isUsed":1},{"districtId":321282,"cityId":321200,"districtName":"靖江","isUsed":1},{"districtId":321283,"cityId":321200,"districtName":"泰兴","isUsed":1}]},{"cityId":321300,"provId":320000,"cityName":"宿迁","isUsed":1,"districts":[{"districtId":321302,"cityId":321300,"districtName":"宿城","isUsed":1},{"districtId":321311,"cityId":321300,"districtName":"宿豫","isUsed":1},{"districtId":321322,"cityId":321300,"districtName":"沭阳","isUsed":1},{"districtId":321323,"cityId":321300,"districtName":"泗阳","isUsed":1},{"districtId":321324,"cityId":321300,"districtName":"泗洪","isUsed":1}]}]
             */

            private int provId;
            private String provName;
            private int isUsed;
            private List<CitiesBean> cities;

            public int getProvId() {
                return provId;
            }

            public void setProvId(int provId) {
                this.provId = provId;
            }

            public String getProvName() {
                return provName;
            }

            public void setProvName(String provName) {
                this.provName = provName;
            }

            public int getIsUsed() {
                return isUsed;
            }

            public void setIsUsed(int isUsed) {
                this.isUsed = isUsed;
            }

            public List<CitiesBean> getCities() {
                return cities;
            }

            public void setCities(List<CitiesBean> cities) {
                this.cities = cities;
            }

            public static class CitiesBean implements Serializable{
                /**
                 * cityId : 320100
                 * provId : 320000
                 * cityName : 南京
                 * isUsed : 1
                 * districts : [{"districtId":320102,"cityId":320100,"districtName":"玄武","isUsed":1},{"districtId":320104,"cityId":320100,"districtName":"秦淮","isUsed":1},{"districtId":320105,"cityId":320100,"districtName":"建邺","isUsed":1},{"districtId":320106,"cityId":320100,"districtName":"鼓楼","isUsed":1},{"districtId":320111,"cityId":320100,"districtName":"浦口","isUsed":1},{"districtId":320113,"cityId":320100,"districtName":"栖霞","isUsed":1},{"districtId":320114,"cityId":320100,"districtName":"雨花台","isUsed":1},{"districtId":320115,"cityId":320100,"districtName":"江宁","isUsed":1},{"districtId":320116,"cityId":320100,"districtName":"六合","isUsed":1},{"districtId":320117,"cityId":320100,"districtName":"溧水","isUsed":1},{"districtId":320118,"cityId":320100,"districtName":"高淳","isUsed":1}]
                 */

                private int cityId;
                private int provId;
                private String cityName;
                private int isUsed;
                private List<DistrictsBean> districts;

                public int getCityId() {
                    return cityId;
                }

                public void setCityId(int cityId) {
                    this.cityId = cityId;
                }

                public int getProvId() {
                    return provId;
                }

                public void setProvId(int provId) {
                    this.provId = provId;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public int getIsUsed() {
                    return isUsed;
                }

                public void setIsUsed(int isUsed) {
                    this.isUsed = isUsed;
                }

                public List<DistrictsBean> getDistricts() {
                    return districts;
                }

                public void setDistricts(List<DistrictsBean> districts) {
                    this.districts = districts;
                }

                public static class DistrictsBean implements Serializable{
                    /**
                     * districtId : 320102
                     * cityId : 320100
                     * districtName : 玄武
                     * isUsed : 1
                     */

                    private int districtId;
                    private int cityId;
                    private String districtName;
                    private int isUsed;

                    public int getDistrictId() {
                        return districtId;
                    }

                    public void setDistrictId(int districtId) {
                        this.districtId = districtId;
                    }

                    public int getCityId() {
                        return cityId;
                    }

                    public void setCityId(int cityId) {
                        this.cityId = cityId;
                    }

                    public String getDistrictName() {
                        return districtName;
                    }

                    public void setDistrictName(String districtName) {
                        this.districtName = districtName;
                    }

                    public int getIsUsed() {
                        return isUsed;
                    }

                    public void setIsUsed(int isUsed) {
                        this.isUsed = isUsed;
                    }
                }
            }
        }
    }
}
