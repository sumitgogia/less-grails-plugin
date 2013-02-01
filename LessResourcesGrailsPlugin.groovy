import org.grails.plugin.resource.BundleResourceMapper
import org.grails.plugin.resource.CSSBundleResourceMeta
import org.grails.plugin.resource.ResourceModule
import org.grails.plugin.resource.ResourceProcessor
import org.grails.plugin.resource.ResourceTagLib


class LessResourcesGrailsPlugin {
    // the plugin version
    def version = "1.3.0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [resources:'1.0 > *']
    def loadAfter = ['resources']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Karol Balejko"
    def authorEmail = "kb@groovydev.com"
    def organization = [ name: "GroovyDev", url: "http://groovydev.com/" ]
    def title = "Plugin LESS files resource mapper"
    def description = '''Provides LESS files resource mapper. Compile .less files into .css files. Less compiler based on less.js (LESS - Leaner CSS v1.3.0 http://lesscss.org)'''

    def documentation = "https://github.com/groovydev/less-grails-plugin/blob/master/README.md"
    def license = "APACHE"
    def issueManagement = [ system: "github", url: "https://github.com/groovydev/less-grails-plugin/issues" ]
    def scm = [ url: "https://github.com/groovydev/less-grails-plugin" ]

    def doWithSpring = {
        org.grails.plugin.resource.CSSPreprocessorResourceMapper.defaultIncludes.add('**/*.less')
        org.grails.plugin.resource.CSSRewriterResourceMapper.defaultIncludes.add('**/*.less')
		
		BundleResourceMapper.MIMETYPE_TO_RESOURCE_META_CLASS.put('stylesheet/less', CSSBundleResourceMeta)
		List currentTypes = new ResourceModule().bundleTypes
		ResourceModule.metaClass.getBundleTypes = {  currentTypes << 'less' }
		ResourceProcessor.DEFAULT_MODULE_SETTINGS['less'] = [disposition: 'head'  ]
		ResourceTagLib.SUPPORTED_TYPES['less'] = [
			type: "text/css",
			rel: 'stylesheet/less',
			media: 'screen, projection'
		]
    }
}
