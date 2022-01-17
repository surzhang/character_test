package com.yun.service.impl;

import com.yun.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * @ author: zyk
 * @ description:邮件发送类
 * @ date: 2021/12/8 21:11
 * @ param:
 * @ return:
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 用来发送模版邮件
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void send(String to, String subject, Map<String, Integer> map) {
        List<Integer> count = new ArrayList<>();
        for (String s : map.keySet()) {
            count.add(map.get(s));
        }
       // System.out.println(count);

        Collections.sort(count,Collections.reverseOrder());
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> redMap = new HashMap<>();
        list.add(redMap);
        Map<String, Object> blueMap = new HashMap<>();
        list.add(blueMap);
        Map<String, Object> greenMap = new HashMap<>();
        list.add(greenMap);
        Map<String, Object> yellowMap = new HashMap<>();
        list.add(yellowMap);

        redMap.put("introduction", "您的性格是红色性格");
        redMap.put("count", map.get("红色性格"));
        redMap.put("advantage", "【性格特点】：" +
                "积极乐观 情绪波动大起大落\n" +
                "真诚主动 开玩笑不分场合\n" +
                "善于表达 疏于兑现承诺\n" +
                "富有感染力 这山望着那山高\n");
        redMap.put("character", "【性格优势】：\n" +
                "作为个体：高度乐观的积极心态。喜欢自己，也容易接纳别人。把生命当作值得享受的经验。喜欢新鲜、变化和刺激。经常开心，追求快乐。情感丰富而外露。\n" + "<br>" +
                "沟通特点：才思敏捷，善于表达。喜欢通过肢体上的接触传达亲密情感。容易与人攀谈。人越多越亢奋。乐于表达自己的看法\n" + "<br>" +
                "作为朋友：真诚主动，热情洋溢。喜欢交友，善于与陌生人互动。擅长搞笑，是带来乐趣的伙伴。有错就认，很快道歉。喜欢接受别人的肯定和不吝赞美。\n" + "<br>" +
                "对待工作和事业：工作主动，寻找新任务。富有感染力，能够吸引他人参与。不喜欢太多的规定束缚，富有创意。工作以活泼化、丰富化的方式进行。反应快，闪电般开始。\n" + "<br>" +
                "【性格过当】：\n" +
                "作为个体：情绪波动大起大落。变化无常，随意性强。鲁莽冲动，轻信他人，容易上当受骗。虚荣心强，不肯吃苦，贪图享受。不肯承担责任，期待有别人为自己的人生负责。拒绝长大。借放纵来麻痹自己的痛苦和烦恼，而不去认真思考生命的本质。\n" + "<br>" +
                "沟通特点：说话少经大脑思考，脱口而出。对于严肃和敏感的事情也会开玩笑。炫耀自己，夺人话题。注意力分散，不能专注倾听，插话。口无遮拦，不保守秘密。不可靠，光说不练。夸大吹嘘自己的成功。\n" + "<br>" +
                "作为朋友：缺少分寸，过度的玩笑和热情。只想当主角。谈论自己感兴趣的话题，对和自己无关的话题心不在焉。好心办坏事。\n" + "<br>" +
                "对待工作事业：跳槽频率高，这山望着那山高。没有规划，随意性强。没有焦点，把精力分散在太多的不同方向。不切实际地希望所有的工作都要有趣味。很难全神贯注，经常性地走神。异想天开，难以预料。\n");
        blueMap.put("introduction", "您的性格是黄色性格");
        blueMap.put("count", map.get("黄色性格"));
        blueMap.put("advantage", "【性格特点】：" + "行动迅速 死不认错\n" +
                "善于忠告 控制欲强\n" +
                "不感情用事 咄咄逼人\n" +
                "坚持不懈 容易发怒\n");
        blueMap.put("character", "【性格优势】：\n" +
                "作为个体：不达目标，誓不罢休。不停地给自己设定目标以推动前进。强烈的进取心，居安思危。独立性强。有强烈的求胜欲。危难时刻挺身而出。讲究速度和效率。敢于接受挑战并渴望成功。\n" + "<br>" +
                "沟通特点：以务实的方式主导会谈。喜欢主导整个事情进行的方式。能够直接抓住问题的本质。说话用字简明扼要，不喜欢拐弯抹角。不受情绪干扰和控制。\n" + "<br>" +
                "作为朋友：给予解决问题的方法，而非纠缠在过去。迅速提出忠告和方向。直言不讳地提出建议。\n" + "<br>" +
                "对待工作和事业：动作干净利落，讲求效率。能够承担长期高强度的压力。强烈的目标趋向，善于设定目标。高瞻远瞩，有全局观念。善于委派工作。坚持不懈，促成活动。\n" + "<br>" +
                "【性格过当】：\n" +
                "作为个体：自己永远是对的，死不认错。趾高气扬，霸道。只关注自己的感受，不体贴别人的心情和想法。以自我为中心，自私倾向。霸道。脾气暴躁，容易发怒。缺少同情心。傲慢自大，目中无人。\n" + "<br>" +
                "沟通特点：喜欢争辩和冲突。铁石心肠，对情绪表现冷淡。粗线条，简单粗暴。毫无敏感，无力洞察他人内心和理解他人所想。缺乏耐心，是非常糟糕的倾听者。不太能体谅他人，对行事模式不同的人缺少包容度。\n" + "<br>" +
                "作为朋友：大多时候仅保持理性的友谊。讨厌与犹豫不决、能力弱的人互动。试图控制和影响大家的活动，希望他人服从自己而非配合别人。除了工作内容，很少交谈其它话题。情感上习惯与人保持一定的距离。很少对人流露出直接诚挚的关怀。需要你的时候才找你。为别人做主。\n" + "<br>" +
                "对待工作和事业：生活在无尽的工作当中而不是人群中。数量远比质量重要。目标没有完成时，容易发怒且迁怒于人。寻求更多的权力，有极强的控制欲。拒绝为自己和他人放松。完成工作第一，人的事情第二。\n");
        greenMap.put("introduction", "您的性格是绿色性格");
        greenMap.put("count", map.get("绿色性格"));
        greenMap.put("advantage", "【性格特点】：" +
                "温柔祥和 拒绝改变\n" +
                "为他人考虑 胆小被动\n" +
                "心平气和 没有主见\n" +
                "善于协调 缺乏创意\n");
        greenMap.put("character", "【性格优势】：\n" +
                "作为个体：爱静不爱动，有温柔祥和的吸引力和宁静愉悦的气质。和善的天性，做人厚道。追求人际关系的和谐。奉行中庸之道，为人稳定低调。遇事以不变应万变，镇定自若。知足常乐，心态轻松。追求平淡的幸福生活。有松弛感，能融入所有的环境和场合。\n" + "<br>" +
                "作为朋友：从无攻击性。富有同情和关心。宽恕他人对自己的伤害。能接纳所有不同性格的人。对友情的要求不严苛。处处为别人考虑，不吝付出。与之相处轻松自然又没有压力。最佳的垃圾宣泄处，鼓励他们的朋友多谈自己。从不尝试去改变他人。\n" + "<br>" +
                "对待工作和事业：高超的协调人际关系的能力。善于从容地面对压力。巧妙地化解冲突。能超脱游离政治斗争之外，没有敌人。缓步前进以取得思考空间。注重人本管理。推崇一种员工都积极参与的工作环境。尊重员工的独立性，从而博得人心和凝聚力。\n" + "<br>" +
                "【性格过当】：\n" +
                "作为个体：按照惯性来做事，拒绝改变，对于外界变化置若罔闻。懒洋洋的作风，原谅自己的不思进取。懦弱胆小，纵容别人欺压自己。\n" + "<br>" +
                "沟通特点：一拳打在棉花上，毫无反应。没有主见，把压力和负担通通转嫁到他人身上。不会拒绝他人，给自己和他人都带来无穷麻烦。\n" + "<br>" +
                "作为朋友：不负责任的和稀泥。姑息养奸的态度。压抑自己的感受以迁就别人。期待让人人满意，对自己的内心不忠诚。\n" + "<br>" +
                "对待工作和事业：安于现状，不思进取。乐于平庸，缺乏创意。害怕冒风险，缺乏自信。拖拖拉拉。缺少目标。\n");
        yellowMap.put("introduction", "您的性格是蓝色性格");
        yellowMap.put("count", map.get("蓝色性格"));
        yellowMap.put("advantage", "【性格特点】：" +
                "思想深邃 情感脆弱\n" +
                "默默关怀他人 喜好批判和挑剔\n" +
                "敏感而细腻 不主动与人沟通\n" +
                "计划性强 患得患失\n");
        yellowMap.put("character", "【性格优势】：\n" +
                "作为个体：严肃的生活哲学。思想深邃，独立思考而不盲目从众。注重承诺，可靠安全。高标准，追求完美。谦和稳健。深思熟虑，三思而后行。坚韧执着。\n" + "<br>" +
                "沟通特点：享受敏感而有深度的交流。设身处地地体会他人。能记住谈话时共鸣的感情和思想。喜欢小群体交流的思想碰撞。关注谈话的细节。\n" + "<br>" +
                "作为朋友：默默地为他人付出以表示关切和爱。对友谊忠诚不渝。真诚关怀朋友的境遇，善于体贴他人。能够记得特殊的日子。遭遇难关时，极力给予鼓舞安慰。\n" + "<br>" +
                "对待工作和事业：强调制度、程序、规范、细节和流程。做事之前首先计划且严格按照计划去执行。喜欢探究及根据事实行事。\n" + "<br>" +
                "【性格过当】：\n" +
                "作为个体：高度负面的情绪化。猜忌心重，不信任他人。太在意别人的看法和评价，容易沮丧，悲观消极。陷于低落的情绪无法自拔。情感脆弱抑郁，有自怜倾向。\n" + "<br>" +
                "沟通特点：不知不觉地说教和上纲上线。原则性强，不易妥协。强烈期待别人具有敏感度和深度能够理解自己。以为别人能够读懂自己的心思。不太主动与人沟通。\n" + "<br>" +
                "作为朋友：过度敏感，有时很难相处。强烈的不安全感。远离人群。经常怀疑别人的话，不容易相信他人。对待工作和事业对自己和他人常寄予过高而且不切实际的期望。\n");

        List<Map<String, Object>> sortMap = new ArrayList<>();
        for (int i = 0; i < count.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).get("count") == count.get(i) && ((Integer)0 != list.get(j).get("count"))) {
                    sortMap.add(list.get(j));
                    break;
                }
            }
        }
        System.out.println("sortMap" + sortMap.size());
        StringBuffer emailContent = new StringBuffer("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>yimcarson</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"width: 540px;text-align: left\">\n");
        if (sortMap.size() == 1) {
            emailContent.append(" <div>" + sortMap.get(0).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(0).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(0).get("character") + "</div>");
        } else if (sortMap.size() == 2) {
            emailContent.append(" <div>" + sortMap.get(0).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(0).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(0).get("character") + "</div>" +
                    " <div>" + sortMap.get(1).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(1).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(1).get("character") + "</div>");
        } else if (sortMap.size() == 3) {
            emailContent.append(" <div>" + sortMap.get(0).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(0).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(0).get("character") + "</div>" +
                    " <div>" + sortMap.get(1).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(1).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(1).get("character") + "</div>" +
                    " <div>" + sortMap.get(2).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(2).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(2).get("character") + "</div>");
        } else if (sortMap.size() == 4) {
            emailContent.append(" <div>" + sortMap.get(0).get("introduction") + "</div>" +
                    "        <div>" + sortMap.get(0).get("advantage") + "</div>" +
                    "        <br>" +
                    "        <div>" + sortMap.get(0).get("character") + "</div>" +
                    " <div>" + sortMap.get(1).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(1).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(1).get("character") + "</div>" +
                    " <div>" + sortMap.get(2).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(2).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(2).get("character") + "</div>" +
                    " <div>" + sortMap.get(3).get("introduction") + "</div>\n" +
                    "        <div>" + sortMap.get(3).get("advantage") + "</div>\n" +
                    "        <br>" +
                    "        <div>" + sortMap.get(3).get("character") + "</div>");
        }

        emailContent.append("</div>\n" +
                "</body>\n" +
                "</html>\n");
        System.out.println(emailContent.toString());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}