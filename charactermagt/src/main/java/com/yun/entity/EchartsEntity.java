package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ fileName:EchartsEntity
 * @ description:
 * @ author:mxt
 * @ createTime:2021/12/6 22:00
 * @ version:1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchartsEntity {
    /**
     * title
     */
    private Title title;
    /**
     * tooltip
     */
    private Tooltip tooltip;
    /**
     * legend
     */
    private Legend legend;
    /**
     * series
     */
    private List<Series> series;

    /**
     * Title
     */
    @NoArgsConstructor
    @Data
    public static class Title {
        /**
         * text
         */
        private String text;
        /**
         * subtext
         */
        private String subtext;
        /**
         * left
         */
        private String left;
    }

    /**
     * Tooltip
     */
    @NoArgsConstructor
    @Data
    public static class Tooltip {
        /**
         * trigger
         */
        private String trigger;
    }

    /**
     * Legend
     */
    @NoArgsConstructor
    @Data
    public static class Legend {
        /**
         * orient
         */
        private String orient;
        /**
         * left
         */
        private String left;
    }

    /**
     * Series
     */
    @NoArgsConstructor
    @Data
    public static class Series {
        /**
         * name
         */
        private String name;
        /**
         * type
         */
        private String type;
        /**
         * radius
         */
        private String radius;
        /**
         * data
         */
        private List<Data> data;
        /**
         * emphasis
         */
        private Emphasis emphasis;

        /**
         * Emphasis
         */
        @NoArgsConstructor
        @lombok.Data
        public static class Emphasis {
            /**
             * itemStyle
             */
            private ItemStyle itemStyle;

            /**
             * ItemStyle
             */
            @NoArgsConstructor
            @lombok.Data
            public static class ItemStyle {
                /**
                 * shadowBlur
                 */
                private Integer shadowBlur;
                /**
                 * shadowOffsetX
                 */
                private Integer shadowOffsetX;
                /**
                 * shadowColor
                 */
                private String shadowColor;
            }
        }

        /**
         * Data
         */
        @NoArgsConstructor
        @lombok.Data
        public static class Data {
            /**
             * value
             */
            private Integer value;
            /**
             * name
             */
            private String name;
        }
    }
}