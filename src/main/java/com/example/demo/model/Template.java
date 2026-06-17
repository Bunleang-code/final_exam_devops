package com.example.demo.model;

import jakarta.persistence.*;

/**
 * A reusable ID-card theme. Colours drive both the live HTML preview and the
 * iText PDF rendering so the two stay visually consistent.
 */
@Entity
@Table(name = "templates")

public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String code;

    @Column(nullable = false, length = 80)
    private String name;

    /** Organisation / institution name printed on the card header. */
    @Column(length = 120)
    private String organizationName;

    /** Layout key: VERTICAL or HORIZONTAL. */
    @Column(nullable = false, length = 20)
    private String layout = "VERTICAL";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /** Primary brand colour as hex, e.g. #1d4ed8. */
    @Column(nullable = false, length = 7)
    private String primaryColor = "#1d4ed8";

    @Column(nullable = false, length = 7)
    private String secondaryColor = "#e0e7ff";

    @Column(nullable = false, length = 7)
    private String textColor = "#111827";

    @Column(length = 255)
    private String tagline;
}
