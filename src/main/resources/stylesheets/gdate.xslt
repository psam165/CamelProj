<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0" xmlns:ns2="http://service.cxf.javax/">
    <xsl:output method="xml" omit-xml-declaration="yes"/>
    <xsl:template match="/ns2:getDateResponse">
    
          <xsl:value-of select="./return/text()"/>
      
    </xsl:template>
</xsl:stylesheet>