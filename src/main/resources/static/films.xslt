<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="ArrayList">
        <html>
            <body>
                <h1>Films</h1>
                <table border="1">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Country</th>
                    </tr>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="film">
        <tr>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="@name"/></td>
            <td><xsl:value-of select="@genre"/></td>
            <td><xsl:value-of select="@country"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>